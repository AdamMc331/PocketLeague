package com.adammcneilly.pocketleague.core.models

/**
 * The result of a particular [team] within a [Match].
 */
data class MatchTeamResult(
    val score: Int = 0,
    val winner: Boolean = false,
    val team: Team = Team(),
    val players: List<GamePlayerResult> = emptyList(),
    val stats: Stats? = null,
)
