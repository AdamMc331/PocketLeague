package com.adammcneilly.pocketleague.data.match.test

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.EventStage
import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.data.match.MatchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeMatchRepository : MatchRepository {
    val matchDetailsById: MutableMap<Match.Id, Match> = mutableMapOf()

    lateinit var pastWeeksMatches: List<Match>

    lateinit var upcomingMatches: List<Match>

    override fun getMatchDetail(matchId: Match.Id): Flow<Match> {
        return flowOf(matchDetailsById[matchId]!!)
    }

    override fun getMatchesInDateRange(startDateUTC: String, endDateUTC: String): Flow<List<Match>> {
        return flowOf(pastWeeksMatches)
    }

    override fun getUpcomingMatches(): Flow<List<Match>> {
        return flowOf(upcomingMatches)
    }

    override suspend fun fetchAndPersistUpcomingMatches(): Result<Unit> {
        return Result.success(Unit)
    }

    override fun getMatchesForEventStage(eventId: Event.Id, stageId: EventStage.Id): Flow<List<Match>> {
        return flowOf(emptyList())
    }

    override fun getPastWeeksMatchesForTeams(teamIds: List<String>): Flow<List<Match>> {
        return flowOf(emptyList())
    }
}
