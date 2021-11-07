package com.adammcneilly.pocketleague.teamlist.data

import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.core.models.Team
import javax.inject.Inject

class DemoTeamListService @Inject constructor() : TeamListService {
    override suspend fun fetchAllTeams(): Result<List<Team>> {
        val teams = listOf(
            Team(
                name = "eUnited",
                logoImageUrl = "",
                roster = emptyList(),
            ),
            Team(
                name = "Pittsburgh Knights",
                logoImageUrl = "",
                roster = emptyList(),
            ),
            Team(
                name = "G2 Esports",
                logoImageUrl = "",
                roster = emptyList(),
            ),
            Team(
                name = "Ghost Gaming",
                logoImageUrl = "",
                roster = emptyList(),
            ),
            Team(
                name = "The General NRG",
                logoImageUrl = "",
                roster = emptyList(),
            ),
            Team(
                name = "Spacestation Gaming",
                logoImageUrl = "",
                roster = emptyList(),
            ),
        )

        return Result.Success(teams)
    }
}
