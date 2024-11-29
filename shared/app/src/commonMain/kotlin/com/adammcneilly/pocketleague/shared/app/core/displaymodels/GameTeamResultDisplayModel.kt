package com.adammcneilly.pocketleague.shared.app.core.displaymodels

import com.adammcneilly.pocketleague.core.models.GamePlayerResult
import com.adammcneilly.pocketleague.core.models.GameTeamResult
import com.adammcneilly.pocketleague.shared.app.core.models.GamePlayerResult
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
        team = result.team.toOverviewDisplayModel(),
        goals = result.goals,
        winner = result.winner,
        players = result.players
            .sortedByDescending { it.stats.core.score }
            .map {
                GamePlayerResultDisplayModel(it)
            },
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
