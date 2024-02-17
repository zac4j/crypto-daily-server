package cn.zacash.controller

import cn.zacash.models.*
import cn.zacash.repository.CoinRepository
import cn.zacash.service
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.gson.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.concurrent.timer

class CoinController(private val repository: CoinRepository) {
    companion object {
        private const val API_ENDPOINT = "https://api.coingecko.com/api/v3"
        private const val PATH_COIN_MARKET =
            "/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=20&page=1&sparkline=false"
        private const val PATH_MARKET_TRENDS = "/search/trending"
    }

    private val client = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            gson()
        }
        install(Logging) {
            level = LogLevel.INFO
        }
    }

    /**
     * Start the work, fetch the crypto data every 6h.
     */
    fun startWork() {
        scheduleJob()
    }

    private suspend fun fetchCoinMarket() {
        val response = client.get(API_ENDPOINT + PATH_COIN_MARKET)
        if (response.status == HttpStatusCode.OK) {
            val coinMarket = response.body<List<CoinMarket>>()
            coinMarket.forEach { market ->
                repository.saveTopCoins(market.toCoin())
            }
        }
    }

    private suspend fun fetchMarketTrends() {
        val response = client.get(API_ENDPOINT + PATH_MARKET_TRENDS)
        if (response.status == HttpStatusCode.OK) {
            val marketTrends = response.body<MarketTrends>()
            marketTrends.coins.forEach { coinTrend ->
                val trendingCoin = coinTrend.item.toTrendingCoin()
                repository.saveTrendingCoin(trendingCoin)
            }

            marketTrends.nfts.forEach { nftTrend ->
                val trendingNFT = nftTrend.toTrendingNFT()
                repository.saveTrendingNFT(trendingNFT)
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun scheduleJob() {
        timer(period = (6 * 60 * 60 * 1000L)) {
            GlobalScope.launch {
                repository.clearAll()
            }
        }

        timer(initialDelay = 2000L, period = 6 * 60 * 60 * 1000L) {
            GlobalScope.launch {
                fetchCoinMarket()
                fetchMarketTrends()
            }
        }
    }

}
