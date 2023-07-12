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

fun GameDetailDisplayModel.Companion.variations(): List<GameDetailDisplayModel> {
    return listOf(
        this.blueWinner(),
        this.orangeWinner(),
        this.orangeWinner().copy(
            otLabel = "OT +12:02",
        ),
    )
}

fun GameDetailDisplayModel.Companion.placeholders(): List<GameDetailDisplayModel> {
    return listOf(
        this.placeholder,
        this.placeholder,
        this.placeholder,
    )
}
