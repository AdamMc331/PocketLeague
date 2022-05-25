package com.adammcneilly.pocketleague.core.displaymodels

import com.adammcneilly.pocketleague.core.models.GameTeamResult

/**
 * User friendly explanation of a team's performance within a game.
 */
data class GameTeamResultDisplayModel(
    val team: TeamOverviewDisplayModel = TeamOverviewDisplayModel(),
    val score: String = "",
    val winner: Boolean = false,
)

/**
 * Converts a [GameTeamResult] to a [GameTeamResultDisplayModel].
 */
fun GameTeamResult.toDisplayModel(): GameTeamResultDisplayModel {
    return GameTeamResultDisplayModel(
        team = this.team.toOverviewDisplayModel(),
        score = this.goals.toString(),
        winner = this.winner,
    )
}