package com.adammcneilly.pocketleague.data.remote.test

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.logging.SIMPLE
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import kotlinx.serialization.json.Json

fun mockEngine(
    responses: Map<String, String>,
) = MockEngine {
    val url = it.url.encodedPath
    // The encoded path drops the first slash, but all of our defined endpoints we hit include it.
    val response = responses["/$url"]

    respond(
        content = ByteReadChannel(response!!),
        status = HttpStatusCode.OK,
        headers = headersOf(HttpHeaders.ContentType, "application/json"),
    )
}

fun fakeHttpClient(
    responses: Map<String, String>,
) = HttpClient(mockEngine(responses)) {
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
