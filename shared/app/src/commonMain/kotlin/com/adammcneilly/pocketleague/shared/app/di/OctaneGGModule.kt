package com.adammcneilly.pocketleague.shared.app.di

import com.adammcneilly.pocketleague.shared.app.data.remote.BaseKtorClient
import com.adammcneilly.pocketleague.shared.app.data.remote.baseHttpClient
import com.adammcneilly.pocketleague.shared.app.data.remote.httpClientEngine
import com.adammcneilly.pocketleague.shared.app.debug.debugHttpClientEngine
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import org.koin.core.qualifier.named
import org.koin.dsl.module

private const val MOCK_OCTANE_ENGINE = "mock_octane_engine"
private const val OCTANE_BASE_URL = "octane_base_url"
private const val OCTANE_HTTP_CLIENT = "octane_http_client"
const val OCTANE_GG_CLIENT = "octane_gg_ktor_client"

val octaneGGModule = module {
    /**
     * Defines a [debugHttpClientEngine] for the octane GG api.
     */
    single<HttpClientEngine>(named(MOCK_OCTANE_ENGINE)) {
        debugHttpClientEngine(
            responses = mapOf(
                "/matches" to "files/match_list.json",
                "/matches/123" to "files/match_detail.json",
            ),
        )
    }

    /**
     * Defines the base url for the octane GG api. If using debug data,
     * we should not use a real base URL but just an empty string.
     */
    single<String>(named(OCTANE_BASE_URL)) {
        val useDebugData = get<Boolean>(named(USE_DEBUG_DATA))

        if (useDebugData) {
            ""
        } else {
            "https://zsr.octane.gg/"
        }
    }

    /**
     * If using debug data, we'll supplied our [MOCK_OCTANE_ENGINE] entry.
     * Otherwise, default to the platform specific engine.
     */
    single<HttpClient>(named(OCTANE_HTTP_CLIENT)) {
        val useDebugData = get<Boolean>(named(USE_DEBUG_DATA))

        val engine = if (useDebugData) {
            get<HttpClientEngine>(named(MOCK_OCTANE_ENGINE))
        } else {
            httpClientEngine()
        }

        baseHttpClient(
            engine = engine,
        )
    }

    /**
     * Creates a [BaseKtorClient] using the necessary base URL and http client engines.
     *
     * The specific factories for those dependencies will create with debug implementations,
     * if necessary.
     */
    single<BaseKtorClient>(named(OCTANE_GG_CLIENT)) {
        BaseKtorClient(
            baseURL = get(named(OCTANE_BASE_URL)),
            httpClient = get(named(OCTANE_HTTP_CLIENT)),
        )
    }
}
