package com.adammcneilly.pocketleague.shared.app.debug

import com.adammcneilly.pocketleague.shared.app.data.remote.defaultHttpClient

/**
 * Creates an implementation of our [defaultHttpClient] that uses a [mockEngine].
 */
fun debugHttpClient(
    responses: Map<String, String>,
) = defaultHttpClient(debugHttpClientEngine(responses))
