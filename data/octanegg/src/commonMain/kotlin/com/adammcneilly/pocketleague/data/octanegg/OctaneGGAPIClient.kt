package com.adammcneilly.pocketleague.data.octanegg

import com.adammcneilly.pocketleague.data.remote.BaseAPIClient

/**
 * An instance of a [BaseAPIClient] that makes all requests to the octane.gg API.
 */
object OctaneGGAPIClient : BaseAPIClient(
    baseURL = "https://zsr.octane.gg/",
)
