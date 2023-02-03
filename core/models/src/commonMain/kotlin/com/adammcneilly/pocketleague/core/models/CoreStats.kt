package com.adammcneilly.pocketleague.core.models

/**
 * The core statistics for a player or team within a match or game.
 */
data class CoreStats(
    val shots: Int = 0,
    val goals: Int = 0,
    val saves: Int = 0,
    val assists: Int = 0,
    val score: Int = 0,
    val shootingPercentage: Float = 0F
)
