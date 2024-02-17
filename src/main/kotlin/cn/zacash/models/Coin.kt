package cn.zacash.models

import org.bson.codecs.pojo.annotations.BsonId
import java.math.BigDecimal

data class Coin(
    @BsonId
    val id: String,
    val name: String,
    val symbol: String,
    val image: String,
    val marketCap: Long,
    val marketCapRank: Int,
    val price: BigDecimal,
    val priceChange: BigDecimal,
    val priceChangePercentage: Double,
    val lastUpdated: String
)
