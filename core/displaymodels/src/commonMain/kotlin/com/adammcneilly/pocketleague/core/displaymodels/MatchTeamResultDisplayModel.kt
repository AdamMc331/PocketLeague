package com.adammcneilly.pocketleague.core.displaymodels

import com.adammcneilly.pocketleague.core.models.MatchTeamResult

/**
 * User friendly explanation for how a team played during a match.
 */
data class MatchTeamResultDisplayModel(
    val team: TeamOverviewDisplayModel = TeamOverviewDisplayModel(),
    val score: Int = 0,
    val winner: Boolean = false,
    val isPlaceholder: Boolean = false,
)

/**
 * Converts a [MatchTeamResult] to a [MatchTeamResultDisplayModel].
 */
fun MatchTeamResult.toDisplayModel(): MatchTeamResultDisplayModel {
    return MatchTeamResultDisplayModel(
        team = this.team.toOverviewDisplayModel(),
        score = this.score,
        winner = this.winner,
    )
}
