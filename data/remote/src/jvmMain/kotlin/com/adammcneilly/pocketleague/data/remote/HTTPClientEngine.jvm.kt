package com.adammcneilly.pocketleague.data.remote

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.cio.CIO

/**
 * Return a [CIO] [HttpClientEngine] for the JVM platform.
 */
actual fun provideHttpClientEngine(): HttpClientEngine {
    return CIO.create()
}
