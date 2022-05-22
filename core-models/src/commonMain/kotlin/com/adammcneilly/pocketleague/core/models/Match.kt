package com.adammcneilly.pocketleague.core.models

import kotlinx.datetime.LocalDateTime

/**
 * A [Match] is any competition between two teams. Could be a single game, a series,
 * or even a best of set.
 */
data class Match(
    val id: String = "",
    val event: Event = Event(),
    val date: LocalDateTime? = null,
    val blueTeam: MatchTeamResult = MatchTeamResult(),
    val orangeTeam: MatchTeamResult = MatchTeamResult(),
)
