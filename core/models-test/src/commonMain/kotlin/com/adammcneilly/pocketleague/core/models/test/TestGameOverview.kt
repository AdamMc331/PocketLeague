package com.adammcneilly.pocketleague.core.models.test

import com.adammcneilly.pocketleague.core.models.GameOverview

val TestModel.gameOverview: GameOverview
    get() = GameOverview(
        id = "1234",
        blueScore = 7,
        orangeScore = 1,
        durationSeconds = 300,
    )
