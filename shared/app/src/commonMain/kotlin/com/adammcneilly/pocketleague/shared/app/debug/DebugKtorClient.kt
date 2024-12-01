package com.adammcneilly.pocketleague.shared.app.debug

import com.adammcneilly.pocketleague.shared.app.data.remote.BaseKtorClient

class DebugKtorClient(
    mockResponses: Map<String, String> = emptyMap(),
) : BaseKtorClient(
        baseURL = "",
        httpClient = debugHttpClient(mockResponses),
    )
