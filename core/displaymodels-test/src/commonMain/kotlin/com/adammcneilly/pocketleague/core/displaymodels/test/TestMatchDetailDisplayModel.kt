package com.adammcneilly.pocketleague.core.displaymodels.test

import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel

val testMatchDetailDisplayModelBlueWinner = MatchDetailDisplayModel(
    matchId = "matchId",
    localDate = "Jan 01, 2000",
    localTime = "12:00",
    eventName = "RLCS World Championship",
    stageName = "Playoffs",
    relativeDateTime = "1d ago",
    orangeTeamResult = testMatchLoserTeamResultDisplayModel,
    blueTeamResult = testMatchWinnerTeamResultDisplayModel,
)

val testMatchDetailDisplayModelOrangeWinner = testMatchDetailDisplayModelBlueWinner.copy(
    orangeTeamResult = testMatchWinnerTeamResultDisplayModel,
    blueTeamResult = testMatchLoserTeamResultDisplayModel,
)
