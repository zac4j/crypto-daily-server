package cn.zacash.repository

import cn.zacash.Consts.DATABASE_NAME
import cn.zacash.Consts.TOP_COINS
import cn.zacash.Consts.TRENDING_COINS
import cn.zacash.Consts.TRENDING_NFTS
import cn.zacash.models.Coin
import cn.zacash.models.TrendingCoin
import cn.zacash.models.TrendingNFT
import kotlinx.coroutines.awaitAll
import org.litote.kmongo.coroutine.CoroutineClient

class CoinRepository(coroutineClient: CoroutineClient) {

    private val db = coroutineClient.getDatabase(DATABASE_NAME)
    private val topCoins = db.getCollection<Coin>(TOP_COINS)
    private val trendingCoins = db.getCollection<TrendingCoin>(TRENDING_COINS)
    private val trendingNFTs = db.getCollection<TrendingNFT>(TRENDING_NFTS)

    suspend fun insertCoin(coin: Coin) = topCoins.insertOne(coin)

    suspend fun getCoin(id: String) = topCoins.findOneById(id)

    suspend fun updateCoin(coin: Coin) = topCoins.updateOneById(coin.id, coin)

    suspend fun saveTopCoins(coin: Coin) = topCoins.save(coin)

    suspend fun saveTrendingCoin(coin: TrendingCoin) = trendingCoins.save(coin)

    suspend fun saveTrendingNFT(nft: TrendingNFT) = trendingNFTs.save(nft)

    suspend fun deleteCoin(id: String) = topCoins.deleteOneById(id)

    suspend fun getTopCoins(): List<Coin> = topCoins.find().toList()

    suspend fun getTrendingCoins(): List<TrendingCoin> = trendingCoins.find().toList()

    suspend fun getTrendingNFTs(): List<TrendingNFT> = trendingNFTs.find().toList()

    suspend fun clearAll() {
        topCoins.drop()
        trendingCoins.drop()
        trendingNFTs.drop()
    }

}