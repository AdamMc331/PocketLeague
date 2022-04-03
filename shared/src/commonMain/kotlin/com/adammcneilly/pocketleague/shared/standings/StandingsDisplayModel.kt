package com.adammcneilly.pocketleague.shared.standings

/**
 * UI representation for a collection of [placements].
 */
data class StandingsDisplayModel(
    val placements: List<StandingsPlacementDisplayModel>,
)
