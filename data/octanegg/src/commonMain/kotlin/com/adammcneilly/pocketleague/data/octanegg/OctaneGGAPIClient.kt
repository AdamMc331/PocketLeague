package com.adammcneilly.pocketleague.data.octanegg

import com.adammcneilly.pocketleague.data.remote.BaseKTORClient

/**
 * An instance of a [BaseKTORClient] that makes all requests to the octane.gg API.
 */
object OctaneGGAPIClient : BaseKTORClient(
    baseURL = "https://zsr.octane.gg/"
)
