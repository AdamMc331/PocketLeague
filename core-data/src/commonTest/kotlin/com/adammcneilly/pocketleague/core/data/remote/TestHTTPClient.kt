package com.adammcneilly.pocketleague.core.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.logging.SIMPLE
import io.ktor.http.ContentType
import kotlinx.serialization.json.Json

/**
 * An implementation of an [HttpClient] that will uses a test engine so we can modify
 * responses in tests.
 */
fun buildTestHTTPClient(
    engine: HttpClientEngine,
): HttpClient {
    return HttpClient(
        engine,
    ) {
        install(JsonFeature) {
            serializer = KotlinxSerializer(
                Json {
                    ignoreUnknownKeys = true
                    acceptContentTypes = acceptContentTypes + ContentType.Any
                }
            )
        }
        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.ALL
        }
    }
}
