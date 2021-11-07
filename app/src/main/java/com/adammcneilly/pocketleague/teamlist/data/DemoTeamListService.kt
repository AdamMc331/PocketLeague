package com.adammcneilly.pocketleague.teamlist.data

import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.core.models.Team
import javax.inject.Inject

class DemoTeamListService @Inject constructor() : TeamListService {
    override suspend fun fetchAllTeams(): Result<List<Team>> {
        val teams = listOf(
            Team(
                name = "eUnited",
                logoImageUrl = "https://liquipedia.net/commons/images/d/d2/EUnited_2020_logostd.png",
                roster = emptyList(),
            ),
            Team(
                name = "Pittsburgh Knights",
                logoImageUrl = "https://liquipedia.net/commons/images/thumb/e/e2/Pittsburgh_Knights_2021_allmode.png/100px-Pittsburgh_Knights_2021_allmode.png",
                roster = emptyList(),
            ),
            Team(
                name = "G2 Esports",
                logoImageUrl = "https://liquipedia.net/commons/images/1/1b/G2_Esports_2019_std.png",
                roster = emptyList(),
            ),
            Team(
                name = "Ghost Gaming",
                logoImageUrl = "https://liquipedia.net/commons/images/thumb/5/5b/Ghost_Gaming_2017_lightmode.png/60px-Ghost_Gaming_2017_lightmode.png",
                roster = emptyList(),
            ),
            Team(
                name = "The General NRG",
                logoImageUrl = "https://liquipedia.net/commons/images/thumb/d/dc/NRG_2020_lightmode.png/200px-NRG_2020_lightmode.png",
                roster = emptyList(),
            ),
            Team(
                name = "Spacestation Gaming",
                logoImageUrl = "https://liquipedia.net/commons/images/thumb/8/8b/Spacestation_Gaming_2021_allmode.png/82px-Spacestation_Gaming_2021_allmode.png",
                roster = emptyList(),
            ),
        )

        return Result.Success(teams)
    }
}
