package com.adammcneilly.pocketleague.core.displaymodels.test

import com.adammcneilly.pocketleague.core.displaymodels.CoreStatsDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchTeamResultDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.PlayerDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel

object TestDisplayModel {
    val matchTeamResultWinner: MatchTeamResultDisplayModel
        get() = MatchTeamResultDisplayModel(
            team = TeamOverviewDisplayModel.knights,
            score = 7,
            winner = true,
            coreStats = coreStats,
            players = emptyList(),
        )

    val matchTeamResultLoser: MatchTeamResultDisplayModel
        get() = MatchTeamResultDisplayModel(
            team = TeamOverviewDisplayModel.g2,
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
}
