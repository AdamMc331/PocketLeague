package com.adammcneilly.pocketleague.core.models

/**
 * A [Match] is any competition between two teams. Could be a single game, a series,
 * or even a best of set.
 */
data class Match(
    val id: String = "",
    val event: Event = Event(),
    val dateUTC: String? = null,
    val blueTeam: MatchTeamResult = MatchTeamResult(),
    val orangeTeam: MatchTeamResult = MatchTeamResult(),
    val stage: EventStage = EventStage(),
    val format: Format = Format(),
    val gameOverviews: List<GameOverview> = emptyList(),
)
