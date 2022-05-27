package com.adammcneilly.pocketleague.core.displaymodels

import com.adammcneilly.pocketleague.core.models.GamePlayerResult

/**
 * User friendly representation of a player's results within a game.
 */
data class GamePlayerResultDisplayModel(
    val playerName: String = "",
    val coreStats: CoreStatsDisplayModel = CoreStatsDisplayModel(),
)

/**
 * Converts a [GamePlayerResult] to its corresponding display model.
 */
fun GamePlayerResult.toDisplayModel(): GamePlayerResultDisplayModel {
    return GamePlayerResultDisplayModel(
        playerName = this.player.tag,
        coreStats = this.stats.core.toDisplayModel(),
    )
}
