package com.adammcneilly.pocketleague.data.match

import com.adammcneilly.pocketleague.core.models.DataState
import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.data.local.PocketLeagueDB
import com.adammcneilly.pocketleague.data.local.mappers.toLocalEvent
import com.adammcneilly.pocketleague.data.local.mappers.toLocalMatch
import com.adammcneilly.pocketleague.data.local.mappers.toLocalTeam
import com.adammcneilly.pocketleague.data.local.mappers.toMatch
import com.adammcneilly.pocketleague.data.octanegg.models.OctaneGGMatch
import com.adammcneilly.pocketleague.data.octanegg.models.OctaneGGMatchListResponse
import com.adammcneilly.pocketleague.data.octanegg.models.toMatch
import com.adammcneilly.pocketleague.data.remote.BaseKTORClient
import com.adammcneilly.pocketleague.sqldelight.MatchWithEventAndTeams
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.datetime.Clock
import kotlin.time.Duration.Companion.days

/**
 * An implementation of [MatchService] that first requests information from the
 * local [database], and syncs data with our remote [apiClient].
 */
class OfflineFirstMatchService(
    private val database: PocketLeagueDB,
    private val apiClient: BaseKTORClient,
) : MatchService {
    override suspend fun fetchMatchDetail(matchId: String): DataState<Match> {
        return apiClient.getResponse<OctaneGGMatch>(
            endpoint = "$MATCHES_ENDPOINT/$matchId",
        ).map { octaneMatch ->
            octaneMatch.toMatch()
        }
    }

    override fun fetchMatches(request: MatchListRequest): Flow<DataState<List<Match>>> {
        TODO("Not yet implemented")
    }

    override fun getPastWeeksMatches(): Flow<List<Match>> {
        return database
            .localMatchQueries
            .selectPastWeekMatches()
            .asFlow()
            .mapToList()
            .map { localMatchList ->
                localMatchList.map(MatchWithEventAndTeams::toMatch)
            }
            .onStart {
                fetchAndPersistRecentMatches()
            }
    }

    private suspend fun fetchAndPersistRecentMatches() {
        val apiResponse = apiClient.getResponse<OctaneGGMatchListResponse>(
            endpoint = MATCHES_ENDPOINT,
            params = mapOf(
                "before" to Clock.System.now(),
                "after" to Clock.System.now().minus(NUM_DAYS_RECENT_MATCHES.days)
            )
        ).map { octaneMatchListResponse ->
            val mappedMatches =
                octaneMatchListResponse.matches?.map(OctaneGGMatch::toMatch).orEmpty()

            mappedMatches
        }

        when (apiResponse) {
            is DataState.Error -> {
                // We need to log this somehow
            }
            DataState.Loading -> {
                // I think we can remove this sealed class type.
            }
            is DataState.Success -> {
                // Persist the data
                database.transaction {
                    // Store the event
                    // Store the teams
                    // Store the match
                    apiResponse.data.forEach { match ->
                        database.localEventQueries
                            .insertFullEventObject(match.event.toLocalEvent())

                        database.localTeamQueries
                            .insertFullTeamObject(match.blueTeam.team.toLocalTeam())

                        database.localTeamQueries
                            .insertFullTeamObject(match.orangeTeam.team.toLocalTeam())

                        database.localMatchQueries
                            .insertFullMatchObject(match.toLocalMatch())
                    }
                }
            }
        }
    }

    companion object {
        private const val MATCHES_ENDPOINT = "/matches"
        private const val NUM_DAYS_RECENT_MATCHES = 7
    }
}
