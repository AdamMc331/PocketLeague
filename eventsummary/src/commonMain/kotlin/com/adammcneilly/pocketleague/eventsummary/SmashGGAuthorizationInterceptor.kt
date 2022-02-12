package com.adammcneilly.pocketleague.eventsummary

import com.apollographql.apollo3.api.http.HttpRequest
import com.apollographql.apollo3.api.http.HttpResponse
import com.apollographql.apollo3.network.http.HttpInterceptor
import com.apollographql.apollo3.network.http.HttpInterceptorChain

/**
 * An [HttpInterceptor] that applies our smash.gg authorization token to the header of each request.
 */
class SmashGGAuthorizationInterceptor : HttpInterceptor {

    override suspend fun intercept(
        request: HttpRequest,
        chain: HttpInterceptorChain
    ): HttpResponse {
        val authToken = SMASH_GG_API_KEY

        val newRequest = request
            .newBuilder()
            .addHeader("Authorization", "Bearer $authToken")
            .build()

        return chain.proceed(newRequest)
    }
}
