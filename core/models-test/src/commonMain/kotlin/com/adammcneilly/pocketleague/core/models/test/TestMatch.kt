package com.adammcneilly.pocketleague.core.models.test

import com.adammcneilly.pocketleague.core.models.Match

val TestModel.matchBlueWinner: Match
    get() = Match(
        id = "1234",
        event = testEvent,
        dateUTC = null,
        blueTeam = TestModel.matchTeamResultWinner,
        orangeTeam = TestModel.matchTeamResultLoser,
        stage = testEventStage,
        format = TestModel.formatBO7,
        gameOverviews = listOf(TestModel.gameOverview),
    )
