package com.adammcneilly.pocketleague.data.remote

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter

/**
 * Whenever we want to add params to a request, we just return a map of param
 * keys and values. The [BaseAPIClient] can map this to the request builder.
 */
typealias RemoteParams = Map<String, String?>

/**
 * Adds all of the [params] to this [HttpRequestBuilder] as long as they're not null.
 */
fun HttpRequestBuilder.addParams(params: RemoteParams) {
    params.forEach { (key, value) ->
        if (value != null) {
            this.parameter(key, value)
        }
    }
}
