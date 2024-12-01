package com.adammcneilly.pocketleague.shared.app.data.octanegg

import com.adammcneilly.pocketleague.shared.app.data.remote.BaseKtorClient
import io.ktor.client.HttpClient

/**
 * An instance of a [BaseKtorClient] that makes all requests to the octane.gg API.
 */
class OctaneGGKtorClient(
    httpClient: HttpClient,
) : BaseKtorClient(
        baseURL = "https://zsr.octane.gg/",
        httpClient = httpClient,
    )
