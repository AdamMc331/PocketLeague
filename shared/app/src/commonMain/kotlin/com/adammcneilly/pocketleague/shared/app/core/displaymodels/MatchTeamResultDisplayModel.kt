package com.adammcneilly.pocketleague.shared.app.core.displaymodels

import com.adammcneilly.pocketleague.shared.app.core.models.MatchTeamResult

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
    constructor(result: MatchTeamResult) : this(
        team = TeamOverviewDisplayModel(result.team),
        score = result.score,
        winner = result.winner,
        players = result.players.map(::GamePlayerResultDisplayModel),
        coreStats = result.stats?.core?.let(::CoreStatsDisplayModel),
    )

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
