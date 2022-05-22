package com.adammcneilly.pocketleague.core.data.models

/**
 * Used to request a list of games for the given [matchId].
 */
data class MatchGamesRequest(
    val matchId: String,
)
