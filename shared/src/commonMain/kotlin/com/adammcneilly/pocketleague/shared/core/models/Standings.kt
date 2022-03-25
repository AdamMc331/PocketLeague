package com.adammcneilly.pocketleague.shared.core.models

/**
 * Represents the standings [placements] for some event.
 */
data class Standings(
    val placements: List<StandingsPlacement>,
)
