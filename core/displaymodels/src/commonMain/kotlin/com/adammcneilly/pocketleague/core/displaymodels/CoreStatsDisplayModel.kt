package com.adammcneilly.pocketleague.core.displaymodels

import com.adammcneilly.pocketleague.core.models.CoreStats

/**
 * User friendly representation of core stats.
 */
data class CoreStatsDisplayModel(
    val score: Int = 0,
    val goals: Int = 0,
    val assists: Int = 0,
    val saves: Int = 0,
    val shots: Int = 0,
)

/**
 * Converts a [CoreStats] entity into its display model.
 */
fun CoreStats.toDisplayModel(): CoreStatsDisplayModel {
    return CoreStatsDisplayModel(
        score = this.score,
        goals = this.goals,
        assists = this.assists,
        saves = this.saves,
        shots = this.shots,
    )
}
