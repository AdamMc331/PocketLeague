package com.adammcneilly.pocketleague.data.match.test

import com.adammcneilly.pocketleague.core.data.DataState
import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.data.match.MatchListRequest
import com.adammcneilly.pocketleague.data.match.MatchService

class FakeMatchService : MatchService {

    override suspend fun fetchMatchDetail(matchId: String): DataState<Match> {
        TODO("Not yet implemented.")
    }

    override suspend fun fetchMatches(request: MatchListRequest): DataState<List<Match>> {
        TODO("Not yet implemented.")
    }
}
