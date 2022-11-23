package com.adammcneilly.pocketleague.core.models.test

import com.adammcneilly.pocketleague.core.models.Match

val TestModel.matchBlueWinner: Match
    get() = Match(
        id = "1234",
        event = TestModel.event,
        dateUTC = null,
        blueTeam = TestModel.matchTeamResultWinner,
        orangeTeam = TestModel.matchTeamResultLoser,
        stage = TestModel.eventStage,
        format = TestModel.formatBO7,
        gameOverviews = listOf(TestModel.gameOverview),
    )
