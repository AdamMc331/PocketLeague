package com.adammcneilly.pocketleague.core.models

/**
 * High level information about a game, that just contains the scores
 * for each team.
 *
 * For detailed info, we can look it up by [id].
 */
data class GameOverview(
    val id: String = "",
    val blueScore: Int = 0,
    val orangeScore: Int = 0,
    val durationSeconds: Int = 0,
)
