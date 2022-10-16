package com.adammcneilly.pocketleague.core.displaymodels.test

import com.adammcneilly.pocketleague.core.displaymodels.MatchTeamResultDisplayModel

val testMatchWinnerTeamResultDisplayModel = MatchTeamResultDisplayModel(
    team = testTeamOverviewDisplayModel,
    score = 7,
    winner = true,
)

val testMatchLoserTeamResultDisplayModel = MatchTeamResultDisplayModel(
    team = testTeamOverviewDisplayModel,
    score = 1,
    winner = false,
)
