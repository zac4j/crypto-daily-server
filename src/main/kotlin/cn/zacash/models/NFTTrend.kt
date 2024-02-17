package cn.zacash.models

import org.bson.codecs.pojo.annotations.BsonId

data class NFTTrend(
    val `data`: NFTMarketData,
    val floor_price_24h_percentage_change: Double,
    val floor_price_in_native_currency: Double,
    val id: String,
    val name: String,
    val native_currency_symbol: String,
    val nft_contract_id: Int,
    val symbol: String,
    val thumb: String
)

data class NFTMarketData(
    val content: Any,
    val floor_price: String,
    val floor_price_in_usd_24h_percentage_change: String,
    val h24_average_sale_price: String,
    val h24_volume: String,
    val sparkline: String
)

data class TrendingNFT(
    @BsonId
    val id: String,
    val name: String,
    val symbol: String,
    val thumb: String,
    val floorPrice: String,
    val floorPrice24hPercentageChange: String,
    val sparkline: String
)

fun NFTTrend.toTrendingNFT(): TrendingNFT {
    return TrendingNFT(
        id,
        name,
        symbol,
        thumb,
        data.floor_price,
        data.floor_price_in_usd_24h_percentage_change,
        data.sparkline
    )
}