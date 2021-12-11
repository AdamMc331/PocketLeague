package com.adammcneilly.pocketleague.core.data.remote.smashgg

import com.adammcneilly.pocketleague.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * A custom [Interceptor] implementation that adds the Smash.gg API key as a header to all requests.
 */
class SmashGGAuthorizationInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain
            .request()
            .newBuilder()
            .addHeader("Authorization", "Bearer ${BuildConfig.SMASH_GG_API_KEY}")
            .build()

        return chain.proceed(newRequest)
    }
}
