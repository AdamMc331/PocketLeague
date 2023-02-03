package com.adammcneilly.pocketleague.core.displaymodels

import com.adammcneilly.pocketleague.core.models.GamePlayerResult

/**
 * User friendly representation of a player's results within a game.
 */
data class GamePlayerResultDisplayModel(
    val player: PlayerDisplayModel,
    val coreStats: CoreStatsDisplayModel,
    val isPlaceholder: Boolean = false
)

/**
 * Converts a [GamePlayerResult] to its corresponding display model.
 */
fun GamePlayerResult.toDisplayModel(): GamePlayerResultDisplayModel {
    return GamePlayerResultDisplayModel(
        player = this.player.toDisplayModel(),
        coreStats = this.stats.core.toDisplayModel()
    )
}
