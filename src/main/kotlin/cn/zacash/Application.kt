package cn.zacash

import cn.zacash.controller.CoinController
import cn.zacash.di.configureKoin
import cn.zacash.plugins.configureMonitoring
import cn.zacash.plugins.configureRouting
import cn.zacash.plugins.configureSerialization
import cn.zacash.repository.CoinRepository
import cn.zacash.service.CoinService
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent.inject
import org.litote.kmongo.coroutine.CoroutineClient

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

val coroutineClient: CoroutineClient by inject(CoroutineClient::class.java)

val repository: CoinRepository by inject(CoinRepository::class.java) {
    parametersOf(coroutineClient)
}

val service: CoinService by inject(CoinService::class.java) {
    parametersOf(repository)
}

val controller by inject<CoinController>(CoinController::class.java) {
    parametersOf(repository)
}

fun Application.module() {
    configureKoin()
    configureSerialization()
    configureMonitoring()
    configureRouting(service)
    controller.startWork()
}
