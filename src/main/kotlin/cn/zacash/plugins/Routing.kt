package cn.zacash.plugins

import cn.zacash.service.CoinService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting(service: CoinService) {
    install(CORS) {
        anyHost()
        allowHeader(HttpHeaders.ContentType)
    }
    routing {

        staticResources("/", "web")

        get("/top/coins") {
            call.respond(service.getTopCoins())
        }

        get("/trending/coins") {
            call.respond(service.getTrendingCoins())
        }

        get("/trending/nfts") {
            call.respond(service.getTrendingNFTs())
        }
    }
}
