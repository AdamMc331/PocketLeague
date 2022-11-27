package com.adammcneilly.pocketleague.data.team

import com.adammcneilly.pocketleague.core.models.DataState
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.data.local.PocketLeagueDatabase
import com.adammcneilly.pocketleague.data.octanegg.models.OctaneGGTeamDetail
import com.adammcneilly.pocketleague.data.octanegg.models.OctaneGGTeamListResponse
import com.adammcneilly.pocketleague.data.octanegg.models.toTeam
import com.adammcneilly.pocketleague.data.remote.BaseKTORClient
import kotlinx.coroutines.flow.Flow

class OfflineFirstTeamService(
    private val database: PocketLeagueDatabase,
    private val apiClient: BaseKTORClient,
) : TeamService {

    override fun getFavoriteTeams(): Flow<List<Team>> {
        return database.getFavoriteTeams()
    }

    override fun getActiveRLCSTeams(): Flow<List<Team>> {
        return database.getAllTeams()
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
                database.storeTeams(apiResponse.data)
            }
        }
    }

    companion object {
        private const val ACTIVE_TEAMS_ENDPOINT = "/teams/active"
    }
}
