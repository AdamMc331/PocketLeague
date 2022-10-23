package com.adammcneilly.pocketleague.core.displaymodels.test

import com.adammcneilly.pocketleague.core.displaymodels.GameTeamResultDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel

val GameTeamResultDisplayModel.Companion.winner: GameTeamResultDisplayModel
    get() = GameTeamResultDisplayModel(
        team = TeamOverviewDisplayModel.test,
        goals = 7,
        winner = true,
        players = emptyList(),
    )

val GameTeamResultDisplayModel.Companion.loser: GameTeamResultDisplayModel
    get() = this.winner.copy(
        goals = 1,
        winner = false,
    )
