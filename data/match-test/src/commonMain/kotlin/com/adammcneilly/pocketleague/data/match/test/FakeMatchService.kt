package com.adammcneilly.pocketleague.data.match.test

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.EventStage
import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.core.models.test.TestModel
import com.adammcneilly.pocketleague.core.models.test.matchBlueWinner
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeMatchService : MatchService {

    var matchListResponse: Result<List<Match>> = Result.success(
        listOf(TestModel.matchBlueWinner),
    )

    var matchDetailResponse: Result<Match> = Result.success(
        TestModel.matchBlueWinner,
    )

    override suspend fun fetchMatchDetail(
        matchId: Match.Id,
    ): Result<Match> {
        return matchDetailResponse
    }

    override fun getPastWeeksMatches(): Flow<List<Match>> {
        return flowOf(listOf(TestModel.matchBlueWinner))
    }

    override fun getUpcomingMatches(): Flow<List<Match>> {
        return flowOf(listOf(TestModel.matchBlueWinner))
    }

    override fun getMatchesForEventStage(
        eventId: Event.Id,
        stageId: EventStage.Id,
    ): Flow<List<Match>> {
        return flowOf(listOf(TestModel.matchBlueWinner))
    }
}
