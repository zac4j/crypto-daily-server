package cn.zacash.models

import java.math.BigDecimal

data class CoinMarket(
    val ath: BigDecimal,
    val ath_change_percentage: Double,
    val ath_date: String,
    val atl: BigDecimal,
    val atl_change_percentage: Double,
    val atl_date: String,
    val circulating_supply: Double,
    val current_price: BigDecimal,
    val fully_diluted_valuation: Long,
    val high_24h: BigDecimal,
    val id: String,
    val image: String,
    val last_updated: String,
    val low_24h: BigDecimal,
    val market_cap: Long,
    val market_cap_change_24h: Double,
    val market_cap_change_percentage_24h: Double,
    val market_cap_rank: Int,
    val max_supply: Double,
    val name: String,
    val price_change_24h: BigDecimal,
    val price_change_percentage_24h: Double,
    val roi: CoinRoi,
    val symbol: String,
    val total_supply: Double,
    val total_volume: Double
)

data class CoinRoi(val times: Double, val currency: String, val percentage: Double)

fun CoinMarket.toCoin(): Coin {
    return Coin(
        id,
        name,
        symbol,
        image,
        market_cap,
        market_cap_rank,
        current_price,
        price_change_24h,
        price_change_percentage_24h,
        last_updated
    )
}