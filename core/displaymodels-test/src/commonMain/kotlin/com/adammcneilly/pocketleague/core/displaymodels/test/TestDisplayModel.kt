package com.adammcneilly.pocketleague.core.displaymodels.test

import com.adammcneilly.pocketleague.core.displaymodels.CoreStatsDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchTeamResultDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel

object TestDisplayModel {
    val matchTeamResultWinner: MatchTeamResultDisplayModel
        get() = MatchTeamResultDisplayModel(
            team = TeamOverviewDisplayModel.knights,
            score = 7,
            winner = true,
            coreStats = coreStats,
        )

    val matchTeamResultLoser: MatchTeamResultDisplayModel
        get() = MatchTeamResultDisplayModel(
            team = TeamOverviewDisplayModel.g2,
            score = 1,
            winner = false,
            coreStats = coreStats,
        )

    val coreStats: CoreStatsDisplayModel
        get() = CoreStatsDisplayModel(
            score = 1000,
            goals = 7,
            assists = 5,
            saves = 3,
            shots = 1,
        )
}
