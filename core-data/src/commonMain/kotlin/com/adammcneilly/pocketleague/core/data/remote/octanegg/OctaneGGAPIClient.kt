package com.adammcneilly.pocketleague.core.data.remote.octanegg

import com.adammcneilly.pocketleague.core.data.DataState
import com.adammcneilly.pocketleague.core.data.remote.defaultHTTPClient
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get

/**
 * Defines the API client information for communicating with the octane.gg API.
 */
class OctaneGGAPIClient(
    val httpClient: HttpClient = defaultHTTPClient,
) {
    val baseURL = "https://zsr.octane.gg/"

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
