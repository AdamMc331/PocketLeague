package com.adammcneilly.pocketleague.shared.app.core.models

/**
 * Records information about how a player performed during a game.
 */
data class GamePlayerResult(
    val player: Player,
    val stats: Stats,
)
