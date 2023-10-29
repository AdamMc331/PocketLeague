package com.adammcneilly.pocketleague.core.displaymodels

import com.adammcneilly.pocketleague.core.models.CoreStats

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
    companion object {
        val placeholder =
            CoreStatsDisplayModel(
                score = 0,
                goals = 0,
                assists = 0,
                saves = 0,
                shots = 0,
                placeholder = true,
            )
    }
}

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
