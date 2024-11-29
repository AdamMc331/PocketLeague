package com.adammcneilly.pocketleague.shared.app.core.displaymodels

import com.adammcneilly.pocketleague.shared.app.core.models.GamePlayerResult

/**
 * User friendly representation of a player's results within a game.
 */
data class GamePlayerResultDisplayModel(
    val player: PlayerDisplayModel,
    val coreStats: CoreStatsDisplayModel,
    val isPlaceholder: Boolean = false,
) {
    constructor(result: GamePlayerResult) : this(
        player = PlayerDisplayModel(result.player),
        coreStats = CoreStatsDisplayModel(result.stats.core),
    )
}
