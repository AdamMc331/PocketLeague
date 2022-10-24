package com.adammcneilly.pocketleague.core.displaymodels.test

import com.adammcneilly.pocketleague.core.displaymodels.GameDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.GameTeamResultDisplayModel

val GameDetailDisplayModel.Companion.blueWinner: GameDetailDisplayModel
    get() = GameDetailDisplayModel(
        orangeTeamResult = GameTeamResultDisplayModel.loser,
        blueTeamResult = GameTeamResultDisplayModel.winner,
        map = "Wasteland",
        gameNumber = "1",
        otLabel = null,
    )

val GameDetailDisplayModel.Companion.orangeWinner: GameDetailDisplayModel
    get() = blueWinner.copy(
        orangeTeamResult = GameTeamResultDisplayModel.winner,
        blueTeamResult = GameTeamResultDisplayModel.loser
    )
