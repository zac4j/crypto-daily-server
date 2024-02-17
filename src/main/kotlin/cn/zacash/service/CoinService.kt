package cn.zacash.service

import cn.zacash.models.Coin
import cn.zacash.models.TrendingCoin
import cn.zacash.models.TrendingNFT
import cn.zacash.repository.CoinRepository

class CoinService(private val repository: CoinRepository) {

    suspend fun getTopCoins(): List<Coin> = repository.getTopCoins()

    suspend fun getTrendingCoins(): List<TrendingCoin> = repository.getTrendingCoins()

    suspend fun getTrendingNFTs(): List<TrendingNFT> = repository.getTrendingNFTs()

    suspend fun findCoinById(id: String): Coin? = repository.getCoin(id)

    suspend fun updateById(coin: Coin): Boolean {
        val updateResult = repository.updateCoin(coin)
        return updateResult.modifiedCount == 1L
    }

    suspend fun deleteCoinById(id: String) = repository.deleteCoin(id)

    fun release() {

    }

}