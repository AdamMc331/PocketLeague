package com.adammcneilly.pocketleague.core.models

/**
 * Represents the standings [placements] for some event.
 */
data class Standings(
    val placements: List<StandingsPlacement>,
)
