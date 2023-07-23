package com.adammcneilly.pocketleague.core.models

import kotlin.jvm.JvmInline

/**
 * A [Match] is any competition between two teams. Could be a single game, a series,
 * or even a best of set.
 */
data class Match(
    val id: Id,
    val event: Event,
    val dateUTC: String?,
    val blueTeam: MatchTeamResult,
    val orangeTeam: MatchTeamResult,
    val stage: EventStage,
    val format: Format,
    val gameOverviews: List<GameOverview>,
    val round: StageRound,
) {

    /**
     * This value class ensures we can have type safety anywhere we plan to use a
     * string identifier for a match.
     */
    @JvmInline
    value class Id(
        val id: String,
    )
}
