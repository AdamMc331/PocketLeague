package com.adammcneilly.pocketleague.shared.app.debug

import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.fullPath
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel

/**
 * Create a custom [MockEngine] that will determine the response to return based on the supplied
 * mock [responses].
 */
fun debugHttpClientEngine(
    responses: Map<String, String>,
) = MockEngine {
    // In a debug situation, we don't need params.
    val url = it.url.fullPath.substringBefore("?")

    val response = responses[url]

    if (response == null) {
        throw IllegalArgumentException(
            """
                No mock response found for url: $url
                Available urls:
                ${responses.keys.joinToString("\n")}
            """.trimIndent(),
        )
    }

    respond(
        content = ByteReadChannel(response),
        status = HttpStatusCode.OK,
        headers = headersOf(HttpHeaders.ContentType, "application/json"),
    )
}
