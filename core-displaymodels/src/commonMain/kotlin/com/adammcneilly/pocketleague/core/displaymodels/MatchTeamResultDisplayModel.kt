package com.adammcneilly.pocketleague.core.displaymodels

/**
 * User friendly explanation for how a team played during a match.
 */
data class MatchTeamResultDisplayModel(
    val team: TeamOverviewDisplayModel = TeamOverviewDisplayModel(),
    val score: String = "",
    val winner: Boolean = false,
)
