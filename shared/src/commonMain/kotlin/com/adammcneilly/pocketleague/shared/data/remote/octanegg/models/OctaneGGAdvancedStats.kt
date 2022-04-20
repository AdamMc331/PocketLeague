package com.adammcneilly.pocketleague.shared.data.remote.octanegg.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Advanced statistics for a player or a team.
 */
@Serializable
data class OctaneGGAdvancedStats(
    @SerialName("goalParticipation")
    val goalParticipation: Int? = null,
    @SerialName("mvp")
    val mvp: Boolean? = null,
    @SerialName("rating")
    val rating: Double? = null
)
