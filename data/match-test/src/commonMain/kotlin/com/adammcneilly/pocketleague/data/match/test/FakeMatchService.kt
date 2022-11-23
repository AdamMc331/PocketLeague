package com.adammcneilly.pocketleague.data.match.test

import com.adammcneilly.pocketleague.core.data.DataState
import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.core.models.test.TestModel
import com.adammcneilly.pocketleague.core.models.test.matchBlueWinner
import com.adammcneilly.pocketleague.data.match.MatchListRequest
import com.adammcneilly.pocketleague.data.match.MatchService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeMatchService : MatchService {

    var matchListResponse: DataState<List<Match>> = DataState.Success(
        listOf(TestModel.matchBlueWinner),
    )

    var matchDetailResponse: DataState<Match> = DataState.Success(
        TestModel.matchBlueWinner,
    )

    override suspend fun fetchMatchDetail(matchId: String): DataState<Match> {
        return matchDetailResponse
    }

    override fun fetchMatches(request: MatchListRequest): Flow<DataState<List<Match>>> {
        return flowOf(matchListResponse)
    }
}
