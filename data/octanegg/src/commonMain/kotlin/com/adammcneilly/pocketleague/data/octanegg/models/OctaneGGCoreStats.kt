package com.adammcneilly.pocketleague.data.octanegg.models

import com.adammcneilly.pocketleague.core.models.CoreStats
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The core stats to track for a player or a team in any given match or stretch of time.
 */
@Serializable
data class OctaneGGCoreStats(
    @SerialName("shots")
    val shots: Int? = null,
    @SerialName("goals")
    val goals: Int? = null,
    @SerialName("saves")
    val saves: Int? = null,
    @SerialName("assists")
    val assists: Int? = null,
    @SerialName("score")
    val score: Int? = null,
    @SerialName("shootingPercentage")
    val shootingPercentage: Float? = null,
)

/**
 * Converts an [OctaneGGCoreStats] entity to a [CoreStats] entity.
 */
fun OctaneGGCoreStats.toCoreStats(): CoreStats {
    return CoreStats(
        shots = this.shots ?: 0,
        goals = this.goals ?: 0,
        saves = this.saves ?: 0,
        assists = this.assists ?: 0,
        score = this.score ?: 0,
        shootingPercentage = this.shootingPercentage ?: 0F,
    )
}
