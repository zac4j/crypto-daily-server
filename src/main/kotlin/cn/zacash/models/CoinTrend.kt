package cn.zacash.models

import com.google.gson.JsonObject
import org.bson.codecs.pojo.annotations.BsonId
import java.util.Date

data class CoinTrend(
    val item: CoinItem
)

data class CoinItem(
    val coin_id: Int,
    val `data`: CoinMarketData,
    val id: String,
    val large: String,
    val market_cap_rank: Int,
    val name: String,
    val price_btc: Double,
    val score: Int,
    val slug: String,
    val small: String,
    val symbol: String,
    val thumb: String
)

data class CoinMarketData(
    val content: Content,
    val market_cap: String,
    val market_cap_btc: String,
    val price: String,
    val price_btc: String,
    val price_change_percentage_24h: JsonObject,
    val sparkline: String,
    val total_volume: String,
    val total_volume_btc: String
)

data class Content(
    val description: String,
    val title: String
)

data class TrendingCoin(
    @BsonId
    val id: String,
    val name: String,
    val symbol: String,
    val image: String,
    val marketCap: String,
    val marketCapRank: Int,
    val price: String,
    val priceChangePercentage: String,
    val lastUpdated: String
)

fun CoinItem.toTrendingCoin(): TrendingCoin {
    return TrendingCoin(
        id,
        name,
        symbol,
        small,
        data.market_cap,
        market_cap_rank,
        data.price,
        String.format("%.2f", data.price_change_percentage_24h.get("usd").asFloat),
        Date().toString()
    )
}