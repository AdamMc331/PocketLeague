package com.adammcneilly.pocketleague.team.implementation.octanegg

import com.adammcneilly.pocketleague.core.data.DataResult
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.http.ContentType
import kotlinx.serialization.json.Json

/**
 * Defines the API client information for communicating with the octane.gg API.
 */
internal class OctaneGGAPIClient {
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
    }

    /**
     * A helper function to wrap all of our API requests in a try/catch and return a [DataResult]
     * object.
     */
    suspend inline fun <reified T : Any> getResponse(
        endpoint: String,
        requestBuilder: HttpRequestBuilder.() -> Unit = {},
    ): DataResult<T> {
        val url = "$baseURL$endpoint"

        return try {
            // KTOR will switch to a different dispatcher, so we don't need to.
            DataResult.Success(httpClient.get(url, requestBuilder))
        } catch (e: Exception) {
            DataResult.Error(e)
        }
    }
}
