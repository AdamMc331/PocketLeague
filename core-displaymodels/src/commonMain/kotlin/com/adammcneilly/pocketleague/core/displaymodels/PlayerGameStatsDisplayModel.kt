package com.adammcneilly.pocketleague.core.displaymodels

/**
 * User friendly representation of a player's results within a game.
 */
data class PlayerGameStatsDisplayModel(
    val playerName: String = "",
    val score: String = "",
    val goals: String = "",
    val assists: String = "",
    val saves: String = "",
    val shots: String = "",
)
