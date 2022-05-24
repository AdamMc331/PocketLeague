package com.adammcneilly.pocketleague.core.displaymodels

/**
 * User friendly explanation of a team's performance within a game.
 */
data class GameTeamResultDisplayModel(
    val team: TeamOverviewDisplayModel = TeamOverviewDisplayModel(),
    val score: String = "",
    val winner: Boolean = false,
)
