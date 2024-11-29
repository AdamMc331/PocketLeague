package com.adammcneilly.pocketleague.shared.app.core.displaymodels

import com.adammcneilly.pocketleague.shared.app.core.models.GameTeamResult

/**
 * User friendly explanation of a team's performance within a game.
 */
data class GameTeamResultDisplayModel(
    val team: TeamOverviewDisplayModel,
    val goals: Int,
    val winner: Boolean,
    val players: List<GamePlayerResultDisplayModel>,
    val isPlaceholder: Boolean = false,
) {
    constructor(result: GameTeamResult) : this(
        team = TeamOverviewDisplayModel(result.team),
        goals = result.goals,
        winner = result.winner,
        players = result.playerResults(),
    )

    companion object {
        val placeholder = GameTeamResultDisplayModel(
            team = TeamOverviewDisplayModel.placeholder,
            goals = 0,
            winner = false,
            players = emptyList(),
            isPlaceholder = true,
        )
    }
}

private fun GameTeamResult.playerResults(): List<GamePlayerResultDisplayModel> {
    return this.players
        .sortedByDescending { player ->
            player.stats.core.score
        }
        .map(::GamePlayerResultDisplayModel)
}
