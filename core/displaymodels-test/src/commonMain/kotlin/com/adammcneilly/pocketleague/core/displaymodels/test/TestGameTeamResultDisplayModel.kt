package com.adammcneilly.pocketleague.core.displaymodels.test

import com.adammcneilly.pocketleague.core.displaymodels.GameTeamResultDisplayModel

val testWinningGameTeamResultDisplayModel = GameTeamResultDisplayModel(
    team = testTeamOverviewDisplayModel,
    goals = 7,
    winner = true,
    players = emptyList(),
)

val testLosingGameTeamResultDisplayModel = testWinningGameTeamResultDisplayModel.copy(
    goals = 1,
    winner = false,
)
