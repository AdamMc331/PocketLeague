package com.adammcneilly.pocketleague.data.remote

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin

/**
 * Return a [Darwin] [HttpClientEngine] for the android platform.
 */
actual fun provideHttpClientEngine(): HttpClientEngine {
    return Darwin.create()
}
