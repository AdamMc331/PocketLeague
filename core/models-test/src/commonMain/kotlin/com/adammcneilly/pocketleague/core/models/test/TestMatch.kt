package com.adammcneilly.pocketleague.core.models.test

import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.core.models.StageRound

val TestModel.matchBlueWinner: Match
    get() = Match(
        id = Match.Id("1234"),
        event = TestModel.event,
        dateUTC = null,
        blueTeam = TestModel.matchTeamResultWinner,
        orangeTeam = TestModel.matchTeamResultLoser,
        stage = TestModel.eventStage,
        format = TestModel.formatBO7,
        gameOverviews = listOf(TestModel.gameOverview),
        round = StageRound(1, "First Round"),
    )
