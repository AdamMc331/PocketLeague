package com.adammcneilly.pocketleague.data.remote.test

import com.adammcneilly.pocketleague.data.remote.BaseKTORClient

class FakeKTORClient(
    mockResponses: Map<String, String> = emptyMap(),
) : BaseKTORClient(
    baseURL = "",
    httpClient = fakeHttpClient(mockResponses),
)
