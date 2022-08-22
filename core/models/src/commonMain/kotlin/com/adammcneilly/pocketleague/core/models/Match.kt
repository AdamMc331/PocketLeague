package com.adammcneilly.pocketleague.core.models

/**
 * A [Match] is any competition between two teams. Could be a single game, a series,
 * or even a best of set.
 */
data class Match(
    val id: String,
    val event: Event,
    val dateUTC: String?,
    val blueTeam: MatchTeamResult,
    val orangeTeam: MatchTeamResult,
    val stage: EventStage,
    val format: Format,
    val gameOverviews: List<GameOverview>,
)
