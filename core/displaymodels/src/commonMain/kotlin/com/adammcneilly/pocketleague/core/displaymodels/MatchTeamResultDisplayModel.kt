package com.adammcneilly.pocketleague.core.displaymodels

import com.adammcneilly.pocketleague.core.models.GamePlayerResult
import com.adammcneilly.pocketleague.core.models.MatchTeamResult

/**
 * User friendly explanation for how a team played during a match.
 */
data class MatchTeamResultDisplayModel(
    val team: TeamOverviewDisplayModel,
    val score: Int,
    val winner: Boolean,
    val coreStats: CoreStatsDisplayModel?,
    val players: List<GamePlayerResultDisplayModel>,
    val isPlaceholder: Boolean = false,
) {

    companion object {
        val placeholder = MatchTeamResultDisplayModel(
            team = TeamOverviewDisplayModel.placeholder,
            score = 0,
            winner = false,
            coreStats = null,
            players = emptyList(),
            isPlaceholder = true,
        )
    }
}

/**
 * Converts a [MatchTeamResult] to a [MatchTeamResultDisplayModel].
 */
fun MatchTeamResult.toDisplayModel(): MatchTeamResultDisplayModel {
    return MatchTeamResultDisplayModel(
        team = this.team.toOverviewDisplayModel(),
        score = this.score,
        winner = this.winner,
        players = this.players.map(GamePlayerResult::toDisplayModel),
        coreStats = this.stats?.core?.toDisplayModel(),
    )
}
