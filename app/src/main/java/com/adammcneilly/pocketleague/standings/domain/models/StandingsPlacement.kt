package com.adammcneilly.pocketleague.standings.domain.models

import com.adammcneilly.pocketleague.core.domain.models.Team

/**
 * Represents the [placement] within standings for a given [team].
 */
data class StandingsPlacement(
    val placement: Int,
    val team: Team,
)
