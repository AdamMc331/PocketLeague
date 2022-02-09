package com.adammcneilly.pocketleague.core.models

/**
 * Represents the [placement] within standings for a given [team].
 */
data class StandingsPlacement(
    val placement: Int,
    val team: Team,
)
