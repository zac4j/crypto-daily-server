package cn.zacash

import cn.zacash.controller.CoinController
import cn.zacash.di.configureKoin
import cn.zacash.plugins.configureMonitoring
import cn.zacash.plugins.configureRouting
import cn.zacash.plugins.configureSerialization
import cn.zacash.repository.CoinRepository
import cn.zacash.service.CoinService
import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.MongoCredential
import com.mongodb.ServerAddress
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent.inject
import org.litote.kmongo.coroutine.CoroutineClient
import java.util.Arrays.asList

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

val coroutineClient: CoroutineClient by inject(CoroutineClient::class.java) {
    val mongoHost = System.getenv("MONGO_HOST") ?: "mongodb"
    val mongoPort = (System.getenv("MONGO_PORT") ?: "27017").toInt()

    val settings = MongoClientSettings.builder()
        .applyConnectionString(ConnectionString("mongodb://$mongoHost:$mongoPort"))
        .applyToClusterSettings { it.hosts(listOf(ServerAddress(mongoHost, mongoPort))) }
        .build()
    parametersOf(settings)
}

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
