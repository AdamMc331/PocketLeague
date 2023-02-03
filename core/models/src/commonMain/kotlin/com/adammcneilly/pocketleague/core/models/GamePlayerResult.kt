package com.adammcneilly.pocketleague.core.models

/**
 * Records information about how a player performed during a game.
 */
data class GamePlayerResult(
    val player: Player,
    val stats: Stats
)
