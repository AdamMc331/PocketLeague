package com.adammcneilly.pocketleague.core.displaymodels.test

import com.adammcneilly.pocketleague.core.displaymodels.MatchTeamResultDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel

val MatchTeamResultDisplayModel.Companion.winner: MatchTeamResultDisplayModel
    get() = MatchTeamResultDisplayModel(
        team = TeamOverviewDisplayModel.knights,
        score = 7,
        winner = true,
    )

val MatchTeamResultDisplayModel.Companion.loser: MatchTeamResultDisplayModel
    get() = MatchTeamResultDisplayModel(
        team = TeamOverviewDisplayModel.g2,
        score = 1,
        winner = false,
    )
