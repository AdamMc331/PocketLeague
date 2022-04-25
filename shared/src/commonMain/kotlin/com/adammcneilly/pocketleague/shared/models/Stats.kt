package com.adammcneilly.pocketleague.shared.models

/**
 * A collection of different types of statistics for a player or team.
 */
data class Stats(
    val core: CoreStats = CoreStats(),
)
