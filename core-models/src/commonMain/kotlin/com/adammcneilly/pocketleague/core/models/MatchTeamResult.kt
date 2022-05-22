package com.adammcneilly.pocketleague.core.models

/**
 * The result of a particular [team] within a [Match].
 */
data class MatchTeamResult(
    val score: Int = -1,
    val winner: Boolean = false,
    val team: Team = Team(),
)
