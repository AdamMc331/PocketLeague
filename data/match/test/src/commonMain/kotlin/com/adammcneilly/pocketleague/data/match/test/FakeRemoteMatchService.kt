package com.adammcneilly.pocketleague.data.match.test

import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.data.match.api.MatchListRequest
import com.adammcneilly.pocketleague.data.match.api.RemoteMatchService

class FakeRemoteMatchService : RemoteMatchService {
    private val mockResponses: MutableMap<MatchListRequest, Result<List<Match>>> = mutableMapOf()

    override suspend fun fetch(
        request: MatchListRequest,
    ): Result<List<Match>> {
        return mockResponses[request]!!
    }

    fun mockResponse(
        request: MatchListRequest,
        response: Result<List<Match>>,
    ) {
        mockResponses[request] = response
    }
}
