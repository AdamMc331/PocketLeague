package com.adammcneilly.pocketleague.core.data.remote.octanegg.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Tracks any number of statistics relevant to a player or team.
 */
@Serializable
data class OctaneGGStats(
    @SerialName("boost")
    val boost: OctaneGGBoostStats? = null,
    @SerialName("core")
    val core: OctaneGGCoreStats? = null,
    @SerialName("demo")
    val demo: OctaneGGDemoStats? = null,
    @SerialName("movement")
    val movement: OctaneGGMovementStats? = null,
    @SerialName("positioning")
    val positioning: OctaneGGPositioningStats? = null
)
