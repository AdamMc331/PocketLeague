package com.adammcneilly.pocketleague.models

/**
 * Represents the [placement] within standings for a given [team].
 */
data class StandingsPlacement(
    val placement: Int,
    val team: Team,
)
