package com.adammcneilly.pocketleague.standings.domain.models

/**
 * Represents the [placement] within standings for a given [teamName].
 */
data class StandingsPlacement(
    val placement: Int,
    val teamName: String,
)
