package com.adammcneilly.pocketleague.core.displaymodels.test

import com.adammcneilly.pocketleague.core.displaymodels.GameTeamResultDisplayModel

val TestDisplayModel.gameTeamResultWinner: GameTeamResultDisplayModel
    get() = GameTeamResultDisplayModel(
        team = TestDisplayModel.knights,
        goals = 7,
        winner = true,
        players = emptyList(),
    )

val TestDisplayModel.gameTeamResultLoser: GameTeamResultDisplayModel
    get() = GameTeamResultDisplayModel(
        team = TestDisplayModel.g2,
        goals = 1,
        winner = false,
        players = emptyList(),
    )
