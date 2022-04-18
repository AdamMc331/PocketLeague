package com.adammcneilly.pocketleague.shared.models

/**
 * The result of a particular [team] within a [Match].
 */
data class MatchTeamResult(
    val score: Int,
    val winner: Boolean,
    val team: Team,
)
