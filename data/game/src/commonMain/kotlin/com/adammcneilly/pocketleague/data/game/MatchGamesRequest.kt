package com.adammcneilly.pocketleague.data.game

import com.adammcneilly.pocketleague.core.models.Match

/**
 * Used to request a list of games for the given [matchId].
 */
data class MatchGamesRequest(
    val matchId: Match.Id,
)
