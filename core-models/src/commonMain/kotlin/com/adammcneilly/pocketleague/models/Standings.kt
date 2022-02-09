package com.adammcneilly.pocketleague.models

/**
 * Represents the standings [placements] for some event.
 */
data class Standings(
    val placements: List<StandingsPlacement>,
)
