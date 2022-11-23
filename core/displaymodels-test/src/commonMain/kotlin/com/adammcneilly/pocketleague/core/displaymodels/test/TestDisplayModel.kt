package com.adammcneilly.pocketleague.core.displaymodels.test

import com.adammcneilly.pocketleague.core.displaymodels.CoreStatsDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchTeamResultDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.PlayerDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.ThemedImageURL

object TestDisplayModel {
    val matchTeamResultWinner: MatchTeamResultDisplayModel
        get() = MatchTeamResultDisplayModel(
            team = TestDisplayModel.knights,
            score = 7,
            winner = true,
            coreStats = coreStats,
            players = emptyList(),
        )

    val matchTeamResultLoser: MatchTeamResultDisplayModel
        get() = MatchTeamResultDisplayModel(
            team = TestDisplayModel.g2,
            score = 1,
            winner = false,
            coreStats = coreStats,
            players = emptyList(),
        )

    val coreStats: CoreStatsDisplayModel
        get() = CoreStatsDisplayModel(
            score = 1000,
            goals = 7,
            assists = 5,
            saves = 3,
            shots = 1,
        )

    val cheese = PlayerDisplayModel(
        id = "cheeseId",
        tag = "CHEESE",
    )

    val sosa = PlayerDisplayModel(
        id = "sosaId",
        tag = "sosa",
    )

    val zps = PlayerDisplayModel(
        id = "zpsId",
        tag = "ZPS",
    )

    val knights = TeamOverviewDisplayModel(
        teamId = "6020bd98f1e4807cc700dc08",
        name = "Knights",
        imageUrl = ThemedImageURL(
            lightThemeImageUrl = "https://griffon.octane.gg/teams/pittsburgh-knights.png",
        ),
    )
}
