package com.adammcneilly.pocketleague.shared.app.di

import com.adammcneilly.pocketleague.shared.app.data.remote.BaseKtorClient
import com.adammcneilly.pocketleague.shared.app.data.remote.baseHttpClient
import com.adammcneilly.pocketleague.shared.app.data.remote.httpClientEngine
import com.adammcneilly.pocketleague.shared.app.debug.debugHttpClientEngine
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val OCTANEGG_CLIENT = "octanegg"
const val DEFAULT_ENGINE = "default_engine"
const val MOCK_OCTANEGG_ENGINE = "mock_octanegg_engine"
const val OCTANE_GG_BASE_URL = "octanegg_base_url"

val remoteModule = module {
    single<HttpClientEngine>(named(MOCK_OCTANEGG_ENGINE)) {
        debugHttpClientEngine(
            responses = mapOf(
                "/matches" to "files/match_list.json",
                "/matches/123" to "files/match_detail.json",
            ),
        )
    }

    single<HttpClientEngine>(named(DEFAULT_ENGINE)) {
        httpClientEngine()
    }

    single<String>(named(OCTANE_GG_BASE_URL)) {
        val useDebugData = get<Boolean>(named(USE_DEBUG_DATA))

        if (useDebugData) {
            ""
        } else {
            "https://zsr.octane.gg/"
        }
    }

    single<HttpClient> {
        val useDebugData = get<Boolean>(named(USE_DEBUG_DATA))

        val engine = if (useDebugData) {
            get<HttpClientEngine>(named(MOCK_OCTANEGG_ENGINE))
        } else {
            get<HttpClientEngine>(named(DEFAULT_ENGINE))
        }

        baseHttpClient(
            engine = engine,
        )
    }

    single<BaseKtorClient>(named(OCTANEGG_CLIENT)) {
        BaseKtorClient(
            baseURL = get(named(OCTANE_GG_BASE_URL)),
            httpClient = get(),
        )
    }
}
