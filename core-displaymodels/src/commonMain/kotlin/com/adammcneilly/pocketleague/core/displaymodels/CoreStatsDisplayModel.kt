package com.adammcneilly.pocketleague.core.displaymodels

import com.adammcneilly.pocketleague.core.models.CoreStats

/**
 * User friendly representation of core stats.
 */
data class CoreStatsDisplayModel(
    val score: String = "",
    val goals: String = "",
    val assists: String = "",
    val saves: String = "",
    val shots: String = "",
)

/**
 * Converts a [CoreStats] entity into its display model.
 */
fun CoreStats.toDisplayModel(): CoreStatsDisplayModel {
    return CoreStatsDisplayModel(
        score = this.score.toString(),
        goals = this.goals.toString(),
        assists = this.assists.toString(),
        saves = this.saves.toString(),
        shots = this.shots.toString(),
    )
}
