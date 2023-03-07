package com.adammcneilly.pocketleague.data.match

import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.data.local.sqldelight.PocketLeagueDB
import com.adammcneilly.pocketleague.data.local.sqldelight.mappers.toLocalEvent
import com.adammcneilly.pocketleague.data.local.sqldelight.mappers.toLocalEventStage
import com.adammcneilly.pocketleague.data.local.sqldelight.mappers.toLocalMatch
import com.adammcneilly.pocketleague.data.local.sqldelight.mappers.toLocalTeam
import com.adammcneilly.pocketleague.data.local.sqldelight.mappers.toMatch
import com.adammcneilly.pocketleague.data.local.sqldelight.util.asFlowList
import com.adammcneilly.pocketleague.data.local.sqldelight.util.asFlowSingle
import com.adammcneilly.pocketleague.sqldelight.MatchWithEventAndTeams
import kotlinx.coroutines.flow.Flow

/**
 * An implementation of [LocalMatchService] which requests information
 * from the supplied [database].
 */
class SQLDelightMatchService(
    private val database: PocketLeagueDB,
) : LocalMatchService {

    override fun getMatchDetail(matchId: String): Flow<Match> {
        return database
            .localMatchQueries
            .selectById(matchId)
            .asFlowSingle(MatchWithEventAndTeams::toMatch)
    }

    override fun getPastWeeksMatches(): Flow<List<Match>> {
        return database
            .localMatchQueries
            .selectPastWeekMatches()
            .asFlowList(MatchWithEventAndTeams::toMatch)
    }

    override fun getUpcomingMatches(): Flow<List<Match>> {
        return database
            .localMatchQueries
            .selectUpcoming()
            .asFlowList(MatchWithEventAndTeams::toMatch)
    }

    override fun getMatchesForEventStage(eventId: String, stageId: String): Flow<List<Match>> {
        return database
            .localMatchQueries
            .selectMatchesByEventStage(eventId, stageId)
            .asFlowList(MatchWithEventAndTeams::toMatch)
    }

    override suspend fun insertMatches(matches: List<Match>) {
        database.transaction {
            matches.forEach { match ->
                database.localEventQueries
                    .insertFullEventObject(match.event.toLocalEvent())

                database.localEventStageQueries
                    .insertFullEventStage(
                        match.stage.toLocalEventStage(
                            eventId = match.event.id,
                        ),
                    )

                database.localTeamQueries
                    .insertFullTeamObject(match.blueTeam.team.toLocalTeam())

                database.localTeamQueries
                    .insertFullTeamObject(match.orangeTeam.team.toLocalTeam())

                database.localMatchQueries
                    .insertFullMatchObject(match.toLocalMatch())
            }
        }
    }

    override fun getPastWeeksMatchesForTeams(teamIds: List<String>): Flow<List<Match>> {
        return database
            .localMatchQueries
            .selectPastWeekForTeams(teamIds)
            .asFlowList(MatchWithEventAndTeams::toMatch)
    }
}
