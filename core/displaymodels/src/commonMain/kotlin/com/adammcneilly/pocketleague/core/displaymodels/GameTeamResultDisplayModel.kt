package com.adammcneilly.pocketleague.core.displaymodels

import com.adammcneilly.pocketleague.core.models.GamePlayerResult
import com.adammcneilly.pocketleague.core.models.GameTeamResult

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

/**
 * Converts a [GameTeamResult] to a [GameTeamResultDisplayModel].
 */
fun GameTeamResult.toDisplayModel(): GameTeamResultDisplayModel {
    return GameTeamResultDisplayModel(
        team = this.team.toOverviewDisplayModel(),
        goals = this.goals,
        winner = this.winner,
        players = this.players
            .sortedByDescending { it.stats.core.score }
            .map(GamePlayerResult::toDisplayModel),
    )
}
