package com.adammcneilly.pocketleague.shared.app.core.models

/**
 * High level information about a game, that just contains the scores
 * for each team.
 *
 * For detailed info, we can look it up by [id].
 */
data class GameOverview(
    val id: String,
    val blueScore: Int,
    val orangeScore: Int,
    val durationSeconds: Int,
)
