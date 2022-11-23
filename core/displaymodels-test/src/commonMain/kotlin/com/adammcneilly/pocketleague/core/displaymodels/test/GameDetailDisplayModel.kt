package com.adammcneilly.pocketleague.core.displaymodels.test

import com.adammcneilly.pocketleague.core.displaymodels.GameDetailDisplayModel

val TestDisplayModel.gameDetailBlueWinner: GameDetailDisplayModel
    get() = GameDetailDisplayModel(
        orangeTeamResult = TestDisplayModel.gameTeamResultLoser,
        blueTeamResult = TestDisplayModel.gameTeamResultWinner,
        map = "Wasteland",
        gameNumber = "1",
        otLabel = null,
    )

val TestDisplayModel.gameDetailOrangeWinner: GameDetailDisplayModel
    get() = TestDisplayModel.gameDetailBlueWinner.copy(
        orangeTeamResult = TestDisplayModel.gameTeamResultWinner,
        blueTeamResult = TestDisplayModel.gameTeamResultLoser,
    )
