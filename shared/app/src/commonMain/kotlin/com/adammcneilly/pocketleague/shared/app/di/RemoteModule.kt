package com.adammcneilly.pocketleague.shared.app.di

import com.adammcneilly.pocketleague.shared.app.data.remote.BaseKtorClient
import com.adammcneilly.pocketleague.shared.app.data.remote.defaultHttpClient
import com.adammcneilly.pocketleague.shared.app.debug.debugHttpClientEngine
import com.adammcneilly.pocketleague.shared.app.debug.readFile
import kotlinx.coroutines.runBlocking
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val OCTANEGG_CLIENT = "octanegg"
const val MOCK_OCTANE_CLIENT_ENGINE = "mock_octanegg_engine"

val remoteModule = module {
    single<BaseKtorClient>(named(OCTANEGG_CLIENT)) {
        runBlocking {
            val matchListJson = readFile("files/match_list.json")
            val matchDetailJson = readFile("files/match_detail.json")

            val engine = debugHttpClientEngine(
                responses = mapOf(
                    "/matches" to matchListJson,
                    "/matches/123" to matchDetailJson,
                ),
            )

            BaseKtorClient(
                baseURL = "",
                httpClient = defaultHttpClient(engine),
            )
        }
    }
}
