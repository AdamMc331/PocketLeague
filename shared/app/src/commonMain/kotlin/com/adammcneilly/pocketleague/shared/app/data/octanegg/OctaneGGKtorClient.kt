package com.adammcneilly.pocketleague.shared.app.data.octanegg

import com.adammcneilly.pocketleague.shared.app.data.remote.BaseKtorClient

/**
 * An instance of a [BaseKtorClient] that makes all requests to the octane.gg API.
 */
object OctaneGGKtorClient : BaseKtorClient(
    baseURL = "https://zsr.octane.gg/",
)
