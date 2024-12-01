package com.adammcneilly.pocketleague.shared.app.data.octanegg.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Statistics for demolitions for a player or team.
 */
@Serializable
data class OctaneGGDemoStats(
    @SerialName("inflicted")
    val inflicted: Int? = null,
    @SerialName("taken")
    val taken: Int? = null,
)
