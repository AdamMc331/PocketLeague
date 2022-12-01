package com.adammcneilly.pocketleague.data.team

import com.adammcneilly.pocketleague.core.models.DataState
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.data.local.PocketLeagueDB
import com.adammcneilly.pocketleague.data.local.mappers.toLocalTeam
import com.adammcneilly.pocketleague.data.local.mappers.toTeam
import com.adammcneilly.pocketleague.data.octanegg.models.OctaneGGTeamDetail
import com.adammcneilly.pocketleague.data.octanegg.models.OctaneGGTeamListResponse
import com.adammcneilly.pocketleague.data.octanegg.models.toTeam
import com.adammcneilly.pocketleague.data.remote.BaseKTORClient
import com.adammcneilly.pocketleague.sqldelight.LocalTeam
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * An implementation of [TeamService] that only returns data from the supplied [database]
 * but uses the given [apiClient] to [sync] data.
 */
class OfflineFirstTeamService(
    private val database: PocketLeagueDB,
    private val apiClient: BaseKTORClient,
) : TeamService {

    override fun getFavoriteTeams(): Flow<List<Team>> {
        return database
            .localTeamQueries
            .selectFavorites()
            .asFlow()
            .mapToList()
            .map { localTeamList ->
                localTeamList.map(LocalTeam::toTeam)
            }
    }

    override fun getActiveRLCSTeams(): Flow<List<Team>> {
        return database
            .localTeamQueries
            .selectAll()
            .asFlow()
            .mapToList()
            .map { localTeamList ->
                localTeamList.map(LocalTeam::toTeam)
            }
    }

    override suspend fun sync() {
        fetchAndPersistActiveRLCSTeams()
    }

    private suspend fun fetchAndPersistActiveRLCSTeams() {
        val apiResponse = apiClient.getResponse<OctaneGGTeamListResponse>(
            endpoint = ACTIVE_TEAMS_ENDPOINT,
        ).map { octaneGGTeamListResponse ->
            octaneGGTeamListResponse.teams
                ?.map(OctaneGGTeamDetail::toTeam)
                .orEmpty()
        }

        when (apiResponse) {
            is DataState.Error -> {
                // Do we log this? How?
            }

            DataState.Loading -> {
                // Do we still need this? Does it actually happen?
            }

            is DataState.Success -> {
                apiResponse.data.forEach { team ->
                    database
                        .localTeamQueries
                        .insertFullTeamObject(team.toLocalTeam())
                }
            }
        }
    }

    companion object {
        private const val ACTIVE_TEAMS_ENDPOINT = "/teams/active"
    }
}
