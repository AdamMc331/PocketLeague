package com.adammcneilly.pocketleague.shared.data.remote.octanegg.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Tracks any number of statistics relevant to a player or team.
 */
@Serializable
data class OctaneGGStats(
    @SerialName("core")
    val core: OctaneGGCoreStats? = null,
)
