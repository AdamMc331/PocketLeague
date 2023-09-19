package com.adammcneilly.pocketleague.data.match.test

import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.data.match.api.LocalMatchService
import com.adammcneilly.pocketleague.data.match.api.MatchListRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeLocalMatchService : LocalMatchService {

    private val insertedMatchLists: MutableList<List<Match>> = mutableListOf()
    private val mockResponses: MutableMap<MatchListRequest, List<Match>> = mutableMapOf()

    override suspend fun insert(data: List<Match>) {
        insertedMatchLists.add(data)
    }

    override fun stream(request: MatchListRequest): Flow<List<Match>> {
        return flowOf(mockResponses[request]!!)
    }

    fun mockResponse(
        request: MatchListRequest,
        response: List<Match>,
    ) {
        mockResponses[request] = response
    }
}
