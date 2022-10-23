package com.adammcneilly.pocketleague.data.team

import com.adammcneilly.pocketleague.core.data.DataState
import com.adammcneilly.pocketleague.core.models.Team

/**
 * Concrete implementation of [TeamService] to return some demo
 * information for quick testing/prototyping.
 */
class DemoTeamService : TeamService {
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
}
