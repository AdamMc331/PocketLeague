package com.adammcneilly.pocketleague.data.match

import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.data.local.sqldelight.PocketLeagueDB
import com.adammcneilly.pocketleague.data.local.sqldelight.mappers.toLocalEvent
import com.adammcneilly.pocketleague.data.local.sqldelight.mappers.toLocalEventStage
import com.adammcneilly.pocketleague.data.local.sqldelight.mappers.toLocalMatch
import com.adammcneilly.pocketleague.data.local.sqldelight.mappers.toLocalTeam
import com.adammcneilly.pocketleague.data.local.sqldelight.mappers.toMatch
import com.adammcneilly.pocketleague.data.local.sqldelight.util.asFlowList
import com.adammcneilly.pocketleague.sqldelight.MatchWithEventAndTeams
import kotlinx.coroutines.flow.Flow

/**
 * An implementation of [MatchSourceOfTruth] that requests and stores information
 * from the supplied [database].
 */
class SQLDelightMatchSourceOfTruth(
    private val database: PocketLeagueDB,
) : MatchSourceOfTruth {

    override suspend fun insertMatches(matches: List<Match>) {
        database.transaction {
            matches.forEach { match ->
                database.localEventQueries
                    .insertOrIgnoreEventObject(match.event.toLocalEvent())

                database.localEventStageQueries
                    .insertOrIgnoreEventStage(
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

    override fun stream(request: MatchListRequest): Flow<List<Match>> {
        val matchQueries = database.localMatchQueries

        val selectQuery = when (request) {
            is MatchListRequest.DateRange -> {
                matchQueries.selectInDateRange(
                    startDateUTC = request.startDateUTC,
                    endDateUTC = request.endDateUTC,
                )
            }

            is MatchListRequest.EventStage -> {
                matchQueries.selectMatchesByEventStage(
                    eventId = request.eventId.id,
                    stageId = request.stageId.id,
                )
            }

            is MatchListRequest.Id -> {
                matchQueries.selectById(request.matchId.id)
            }
        }

        return selectQuery.asFlowList(MatchWithEventAndTeams::toMatch)
    }
}
