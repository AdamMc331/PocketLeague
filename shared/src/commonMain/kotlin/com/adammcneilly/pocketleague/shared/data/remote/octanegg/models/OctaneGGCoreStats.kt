package com.adammcneilly.pocketleague.shared.data.remote.octanegg.models

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
