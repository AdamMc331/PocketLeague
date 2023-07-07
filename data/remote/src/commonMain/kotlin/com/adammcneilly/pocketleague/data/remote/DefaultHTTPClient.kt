package com.adammcneilly.pocketleague.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.KotlinxSerializationConverter
import kotlinx.serialization.json.Json

/**
 * A default implementation of an [HttpClient] that will use an engine defined
 * by the platform it's being used on. This allows us to override for tests.
 */
fun defaultHttpClient(
    engine: HttpClientEngine = provideHttpClientEngine(),
) = HttpClient(engine) {
    install(ContentNegotiation) {
        val converter = KotlinxSerializationConverter(
            Json {
                ignoreUnknownKeys = true
            },
        )
        register(ContentType.Any, converter)
    }
    install(Logging) {
        logger = Logger.SIMPLE
        level = LogLevel.ALL
    }
}
