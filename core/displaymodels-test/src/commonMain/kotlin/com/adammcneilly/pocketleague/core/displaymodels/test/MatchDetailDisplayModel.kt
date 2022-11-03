package com.adammcneilly.pocketleague.core.displaymodels.test

import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel

val MatchDetailDisplayModel.Companion.blueWinner: MatchDetailDisplayModel
    get() = MatchDetailDisplayModel(
        matchId = "matchId",
        localDate = "Jan 01, 2000",
        localTime = "12:00",
        eventName = "RLCS World Championship",
        stageName = "Playoffs",
        relativeDateTime = "1d ago",
        orangeTeamResult = TestDisplayModel.matchTeamResultLoser,
        blueTeamResult = TestDisplayModel.matchTeamResultWinner,
    )

val MatchDetailDisplayModel.Companion.orangeWinner: MatchDetailDisplayModel
    get() = blueWinner.copy(
        orangeTeamResult = TestDisplayModel.matchTeamResultWinner,
        blueTeamResult = TestDisplayModel.matchTeamResultLoser,
    )
