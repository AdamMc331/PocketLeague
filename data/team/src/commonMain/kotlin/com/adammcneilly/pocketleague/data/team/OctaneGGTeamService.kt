package com.adammcneilly.pocketleague.data.team

import com.adammcneilly.pocketleague.core.models.DataState
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.data.octanegg.OctaneGGAPIClient
import com.adammcneilly.pocketleague.data.octanegg.models.OctaneGGTeamDetail
import com.adammcneilly.pocketleague.data.octanegg.models.OctaneGGTeamListResponse
import com.adammcneilly.pocketleague.data.octanegg.models.toTeam
import com.adammcneilly.pocketleague.data.remote.BaseKTORClient

/**
 * Implementation of [TeamService] that returns information via the Octane GG API.
 */
class OctaneGGTeamService(
    private val apiClient: BaseKTORClient,
) : TeamService {

    constructor() : this(OctaneGGAPIClient)

    /**
     * For now, we'll have this function return a list of demo teams. In the future
     * we'll want to swap this with a local data request.
     */
    override suspend fun getFavoriteTeams(): DataState<List<Team>> {

        val rogue = Team(
            id = "6020bc70f1e4807cc7002386",
            name = "Rogue",
            imageUrl = "https://griffon.octane.gg/teams/rogue.png",
        )

        val spacestation = Team(
            id = "6020bc70f1e4807cc7002389",
            name = "Spacestation Gaming",
            imageUrl = "https://griffon.octane.gg/teams/Spacestation_Gaming_2021.png",
        )

        val nrg = Team(
            id = "6020bc70f1e4807cc70023a0",
            name = "NRG Esports",
            imageUrl = "https://griffon.octane.gg/teams/nrg-esports.png",
        )

        return DataState.Success(
            listOf(rogue, spacestation, nrg)
        )
    }

    override suspend fun getActiveRLCSTeams(): DataState<List<Team>> {
        return apiClient.getResponse<OctaneGGTeamListResponse>(
            endpoint = ACTIVE_TEAMS_ENDPOINT,
        ).map { octaneGGTeamListResponse ->
            octaneGGTeamListResponse.teams
                ?.map(OctaneGGTeamDetail::toTeam)
                .orEmpty()
        }
    }

    companion object {
        private const val ACTIVE_TEAMS_ENDPOINT = "/teams/active"
    }
}
