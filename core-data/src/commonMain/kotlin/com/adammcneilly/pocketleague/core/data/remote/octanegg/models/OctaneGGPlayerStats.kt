package com.adammcneilly.pocketleague.core.data.remote.octanegg.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Contains the core [stats] and [advanced] stats for a [player].
 */
@Serializable
data class OctaneGGPlayerStats(
    @SerialName("advanced")
    val advanced: OctaneGGAdvancedStats? = null,
    @SerialName("player")
    val player: OctaneGGPlayer? = null,
    @SerialName("stats")
    val stats: OctaneGGStats? = null
)
