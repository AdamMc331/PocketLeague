package com.adammcneilly.pocketleague.core.displaymodels.test

import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchTeamResultDisplayModel

val MatchDetailDisplayModel.Companion.blueWinner: MatchDetailDisplayModel
    get() = MatchDetailDisplayModel(
        matchId = "matchId",
        localDate = "Jan 01, 2000",
        localTime = "12:00",
        eventName = "RLCS World Championship",
        stageName = "Playoffs",
        relativeDateTime = "1d ago",
        orangeTeamResult = MatchTeamResultDisplayModel.winner,
        blueTeamResult = MatchTeamResultDisplayModel.loser,
    )

val MatchDetailDisplayModel.Companion.orangeWinner: MatchDetailDisplayModel
    get() = blueWinner.copy(
        orangeTeamResult = MatchTeamResultDisplayModel.winner,
        blueTeamResult = MatchTeamResultDisplayModel.loser,
    )
