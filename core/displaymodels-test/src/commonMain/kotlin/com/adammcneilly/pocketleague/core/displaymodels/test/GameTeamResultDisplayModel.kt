package com.adammcneilly.pocketleague.core.displaymodels.test

import com.adammcneilly.pocketleague.core.displaymodels.GameTeamResultDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel

val GameTeamResultDisplayModel.Companion.winner: GameTeamResultDisplayModel
    get() = GameTeamResultDisplayModel(
        team = TeamOverviewDisplayModel.knights,
        goals = 7,
        winner = true,
        players = emptyList(),
    )

val GameTeamResultDisplayModel.Companion.loser: GameTeamResultDisplayModel
    get() = GameTeamResultDisplayModel(
        team = TeamOverviewDisplayModel.g2,
        goals = 1,
        winner = false,
        players = emptyList(),
    )
