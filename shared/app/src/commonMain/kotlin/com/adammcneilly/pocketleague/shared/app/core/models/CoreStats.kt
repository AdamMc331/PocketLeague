package com.adammcneilly.pocketleague.shared.app.core.models

/**
 * The core statistics for a player or team within a match or game.
 */
data class CoreStats(
    val shots: Int,
    val goals: Int,
    val saves: Int,
    val assists: Int,
    val score: Int,
    val shootingPercentage: Float,
)
