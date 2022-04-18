package com.adammcneilly.pocketleague.shared.models

import kotlinx.datetime.LocalDateTime

/**
 * A [Match] is any competition between two teams. Could be a single game, a series,
 * or even a best of set.
 */
data class Match(
    val id: String,
    val event: Event,
    val date: LocalDateTime?,
    val blueTeam: MatchTeamResult,
    val orangeTeam: MatchTeamResult,
)
