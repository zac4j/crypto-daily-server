package cn.zacash

import cn.zacash.plugins.*
import cn.zacash.repository.CoinRepository
import cn.zacash.service.CoinService
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Before
import kotlin.test.*

/**
 * https://github.com/IanArb/conferencesKtor/blob/master/test/service/ConferenceServiceTest.kt
 */
class ApplicationTest {

    @MockK
    lateinit var repository: CoinRepository

    lateinit var service: CoinService

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        service = CoinService(repository)
    }

    @Test
    fun testRoot() = testApplication {
        application {
            configureRouting(service)
        }
        client.get("/top_coins").apply {
            assertEquals(HttpStatusCode.OK, status)
//            assertEquals("Hello World!", bodyAsText())
        }
    }
}
