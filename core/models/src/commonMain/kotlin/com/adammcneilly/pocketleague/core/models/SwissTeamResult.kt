package com.adammcneilly.pocketleague.core.models

/**
 * Definition of how a given [team] performed throughout an entire swiss stage (3-5 rounds,
 * depending on win/loss).
 */
data class SwissTeamResult(
    val team: Team,
    val matchWins: Int,
    val matchLosses: Int,
)
