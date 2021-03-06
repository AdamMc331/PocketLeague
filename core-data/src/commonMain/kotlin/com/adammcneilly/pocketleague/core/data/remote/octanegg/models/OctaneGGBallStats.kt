package com.adammcneilly.pocketleague.core.data.remote.octanegg.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Ball and possession related stats for a player or team.
 */
@Serializable
data class OctaneGGBallStats(
    @SerialName("possessionTime")
    val possessionTime: Double? = null,
    @SerialName("timeInSide")
    val timeInSide: Double? = null
)
