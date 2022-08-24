package com.adammcneilly.pocketleague.data.game

/**
 * Used to request a list of games for the given [matchId].
 */
data class MatchGamesRequest(
    val matchId: String,
)
