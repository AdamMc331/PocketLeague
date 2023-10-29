package com.adammcneilly.pocketleague.data.remote.test

import com.adammcneilly.pocketleague.data.remote.defaultHttpClient
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
fun mockEngine(
    responses: Map<String, String>,
) = MockEngine {
    val url = it.url.fullPath

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

/**
 * Creates an implementation of our [defaultHttpClient] that uses a [mockEngine].
 */
fun fakeHttpClient(
    responses: Map<String, String>,
) = defaultHttpClient(mockEngine(responses))
