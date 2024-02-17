package cn.zacash.models

data class MarketTrends(
    val coins: List<CoinTrend>,
    val nfts: List<NFTTrend>,
    val categories: List<Any>
)