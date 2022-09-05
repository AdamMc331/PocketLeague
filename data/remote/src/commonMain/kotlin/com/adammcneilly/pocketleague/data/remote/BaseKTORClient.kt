package com.adammcneilly.pocketleague.data.remote

import com.adammcneilly.pocketleague.core.data.DataState
import io.ktor.client.HttpClient
import io.ktor.client.request.get

/**
 * Defines the API client information for communicating with the octane.gg API.
 */
open class BaseKTORClient(
    val baseURL: String,
    val httpClient: HttpClient,
) {

    /**
     * By creating a separate constructor, instead of using a default argument, it seems
     * we can trick the compiler into being able to instantiate this class without the
     * need of our other modules depending on ktor.
     *
     * Although it would probably be best if we used an interface.
     */
    constructor(baseURL: String) : this(baseURL, defaultHTTPClient)

    /**
     * A helper function to build the [baseURL] and [endpoint] operation and performs a get request.
     *
     * Will also pass in the supplied [params] as necessary.
     */
    suspend inline fun <reified T : Any> getResponse(
        endpoint: String,
        params: RemoteParams = emptyMap(),
    ): DataState<T> {
        val url = "$baseURL$endpoint"

        return try {
            val apiResult: T = httpClient.get(url) {
                addParams(params)
            }
            DataState.Success(apiResult)
        } catch (e: Exception) {
            DataState.Error(e)
        }
    }
}
