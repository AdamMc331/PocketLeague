package com.adammcneilly.pocketleague.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.request.get

/**
 * Defines the API client information for communicating with the octane.gg API.
 */
open class BaseKTORClient(
    val baseURL: String,
    val httpClient: HttpClient,
) {
    constructor(baseURL: String) : this(baseURL, defaultHttpClient())

    /**
     * A helper function to build the [baseURL] and [endpoint] operation and performs a get request.
     *
     * Will also pass in the supplied [params] as necessary.
     */
    suspend inline fun <reified T : Any> getResponse(
        endpoint: String,
        params: RemoteParams = emptyMap(),
    ): Result<T> {
        val url = "$baseURL$endpoint"

        return try {
            val apiResult: T = httpClient.get(url) {
                addParams(params)
            }
            Result.success(apiResult)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
