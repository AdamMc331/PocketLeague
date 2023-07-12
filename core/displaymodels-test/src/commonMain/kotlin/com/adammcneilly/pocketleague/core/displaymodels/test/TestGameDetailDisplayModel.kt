package com.adammcneilly.pocketleague.core.displaymodels.test

import com.adammcneilly.pocketleague.core.displaymodels.GameDetailDisplayModel

fun GameDetailDisplayModel.Companion.blueWinner(): GameDetailDisplayModel {
    return GameDetailDisplayModel(
        orangeTeamResult = TestDisplayModel.gameTeamResultLoser,
        blueTeamResult = TestDisplayModel.gameTeamResultWinner,
        map = "Wasteland",
        gameNumber = "1",
        otLabel = null,
    )
}

fun GameDetailDisplayModel.Companion.orangeWinner(): GameDetailDisplayModel {
    return GameDetailDisplayModel(
        orangeTeamResult = TestDisplayModel.gameTeamResultWinner,
        blueTeamResult = TestDisplayModel.gameTeamResultLoser,
        map = "Wasteland",
        gameNumber = "1",
        otLabel = null,
    )
}
