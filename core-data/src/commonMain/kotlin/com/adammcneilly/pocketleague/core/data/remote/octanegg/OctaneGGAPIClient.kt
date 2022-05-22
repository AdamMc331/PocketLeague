package com.adammcneilly.pocketleague.core.data.remote.octanegg

import com.adammcneilly.pocketleague.core.data.DataState
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.logging.SIMPLE
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.http.ContentType
import kotlinx.serialization.json.Json

/**
 * Defines the API client information for communicating with the octane.gg API.
 */
class OctaneGGAPIClient {
    val baseURL = "https://zsr.octane.gg/"

    val httpClient = HttpClient {
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

    /**
     * A helper function to build the [baseURL] and [endpoint] operation and performs a get request.
     * Will also pass in the supplied [requestBuilder] as necessary.
     */
    suspend inline fun <reified T : Any> getResponse(
        endpoint: String,
        requestBuilder: HttpRequestBuilder.() -> Unit = {},
    ): DataState<T> {
        val url = "$baseURL$endpoint"

        return try {
            val apiResult: T = httpClient.get(url, requestBuilder)
            DataState.Success(apiResult)
        } catch (e: Exception) {
            DataState.Error(e)
        }
    }
}
