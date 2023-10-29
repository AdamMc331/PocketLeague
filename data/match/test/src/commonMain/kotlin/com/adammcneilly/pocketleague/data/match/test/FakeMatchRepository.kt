package com.adammcneilly.pocketleague.data.match.test

import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.data.match.api.MatchListRequest
import com.adammcneilly.pocketleague.data.match.api.MatchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeMatchRepository : MatchRepository {
    private val mockResponses: MutableMap<MatchListRequest, List<Match>> = mutableMapOf()

    override fun stream(
        request: MatchListRequest,
        refreshCache: Boolean,
    ): Flow<List<Match>> {
        return flowOf(mockResponses[request]!!)
    }

    fun mockResponse(
        request: MatchListRequest,
        response: List<Match>,
    ) {
        mockResponses[request] = response
    }
}
