package com.adammcneilly.pocketleague.standings.domain.models

/**
 * Represents the standings [placements] for some event.
 */
data class Standings(
    val placements: List<StandingsPlacement>,
)
