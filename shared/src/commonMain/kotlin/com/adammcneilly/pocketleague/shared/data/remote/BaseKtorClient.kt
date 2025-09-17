package com.adammcneilly.pocketleague.shared.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.KotlinxSerializationConverter
import kotlinx.serialization.json.Json

/**
 * Whenever we want to add params to a request, we just return a map of param
 * keys and values. The [BaseKtorClient] can map this to the request builder.
 */
private typealias RemoteParams = Map<String, Any?>

/**
 * Creates a default [httpClient] that can make requests to the supplied [baseURL].
 *
 * You can either subclass this with a specific client type,
 * like `object GitHubClient : BaseKtorClient("https://api.github.com")`,
 * or repurpose this class to represent a specific client instead.
 */
open class BaseKtorClient(
    val baseURL: String,
) {
    val httpClient = HttpClient {
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

    /**
     * A helper function to build the [baseURL] and [endpoint] operation and performs a get request.
     * Will also pass in the supplied [params] as necessary.
     *
     * NOTE that it is expected for endpoint to begin with a forward slash (/), it is not automatically
     * included into the full URL.
     *
     * You can call this function to get a response typed to the given generic, like so:
     * val eventResult: Result<Event> = apiClient.getResponse<Event>(endpoint = "/events/123")
     */
    @Suppress("TooGenericExceptionCaught")
    suspend inline fun <reified T : Any> getResponse(
        endpoint: String,
        params: RemoteParams = emptyMap(),
    ): Result<T> {
        val url = "$baseURL$endpoint"

        return try {
            val apiResult: T = httpClient
                .get(url) {
                    addParams(params)
                }.body()
            Result.success(apiResult)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

/**
 * Adds all of the [params] to this [HttpRequestBuilder] as long as they're not null.
 */
fun HttpRequestBuilder.addParams(
    params: RemoteParams,
) {
    params.forEach { (key, value) ->
        if (value != null) {
            this.parameter(key, value)
        }
    }
}
