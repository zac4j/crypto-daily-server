package cn.zacash.service

import cn.zacash.models.*
import cn.zacash.plugins.*
import cn.zacash.repository.CoinRepository
import cn.zacash.service.CoinService
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import io.mockk.*
import io.mockk.impl.annotations.MockK
import java.math.BigDecimal
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import kotlin.test.*

class CoinServiceTest {

    @MockK
    lateinit var repository: CoinRepository

    lateinit var service: CoinService

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        service = CoinService(repository)
    }

    @Test
    fun `verify get empty list of top coins`() = runBlockingTest {
        coEvery { repository.getTopCoins() } returns emptyList()

        assertEquals(0, service.getTopCoins().size)
    }

    @Test
    fun `verify get a list of top coins`() = runBlockingTest {
        coEvery { repository.getTopCoins() } returns provideTopCoins()

        assertEquals(1, service.getTopCoins().size)
    }

    @Test
    fun `verify get empty list of trending coins`() = runBlockingTest {
        coEvery { repository.getTrendingCoins() } returns emptyList()

        assertEquals(0, service.getTrendingCoins().size)
    }

    @Test
    fun `verify get empty list of trending nfts`() = runBlockingTest {
        coEvery { repository.getTrendingNFTs() } returns emptyList()

        assertEquals(0, service.getTrendingNFTs().size)
    }

    @Test
    fun `verify get a list of trending nfts`() = runBlockingTest {
        coEvery { repository.getTrendingNFTs() } returns provideTrendingNFTs()

        assertEquals(1, service.getTrendingNFTs().size)
    }

    private fun provideTopCoins() = listOf(Coin("1", "Bitcoin", "BTC", "", 1L, 1, BigDecimal(56000), BigDecimal(1000), 2.0, ""))

    private fun provideTrendingNFTs() = listOf(TrendingNFT("", "Bosten", "BOS", "", "5.0 ETH", "", ""))
}