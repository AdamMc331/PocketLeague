package com.adammcneilly.pocketleague.data.remote

import io.ktor.client.engine.HttpClientEngine

/**
 * Provide an [HttpClientEngine] for a given platform.
 */
expect fun provideHttpClientEngine(): HttpClientEngine
