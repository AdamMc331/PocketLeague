package com.adammcneilly.pocketleague.core.displaymodels.test

import com.adammcneilly.pocketleague.core.displaymodels.GameDetailDisplayModel

val testBlueWinningGameDetailDisplayModel = GameDetailDisplayModel(
    orangeTeamResult = testLosingGameTeamResultDisplayModel,
    blueTeamResult = testWinningGameTeamResultDisplayModel,
    map = "Wasteland",
    gameNumber = "1",
    otLabel = null,
)

val testOrangeWinningGameDetailDisplayModel = testBlueWinningGameDetailDisplayModel.copy(
    orangeTeamResult = testWinningGameTeamResultDisplayModel,
    blueTeamResult = testLosingGameTeamResultDisplayModel,
)
