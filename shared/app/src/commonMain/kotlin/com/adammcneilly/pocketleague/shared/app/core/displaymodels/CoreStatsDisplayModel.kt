package com.adammcneilly.pocketleague.shared.app.core.displaymodels

import com.adammcneilly.pocketleague.shared.app.core.models.CoreStats

/**
 * User friendly representation of core stats.
 */
data class CoreStatsDisplayModel(
    val score: Int,
    val goals: Int,
    val assists: Int,
    val saves: Int,
    val shots: Int,
    val placeholder: Boolean = false,
) {
    constructor(coreStats: CoreStats) : this(
        score = coreStats.score,
        goals = coreStats.goals,
        assists = coreStats.assists,
        saves = coreStats.saves,
        shots = coreStats.shots,
    )

    companion object {
        val placeholder = CoreStatsDisplayModel(
            score = 0,
            goals = 0,
            assists = 0,
            saves = 0,
            shots = 0,
            placeholder = true,
        )
    }
}
