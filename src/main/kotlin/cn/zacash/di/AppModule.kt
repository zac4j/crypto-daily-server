package cn.zacash.di

import cn.zacash.controller.CoinController
import cn.zacash.repository
import cn.zacash.repository.CoinRepository
import cn.zacash.service.CoinService
import io.ktor.server.application.*
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.litote.kmongo.coroutine.CoroutineClient
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val appModule = module {
    single { (_: Unit) -> KMongo.createClient().coroutine }
    single { (client: CoroutineClient) -> CoinRepository(client) }
    single { (repository: CoinRepository) -> CoinService(repository) }
    single { (_: Unit) -> CoinController(repository) }
}

fun Application.configureKoin() {
    install(Koin) {
        modules(appModule)
    }
}

