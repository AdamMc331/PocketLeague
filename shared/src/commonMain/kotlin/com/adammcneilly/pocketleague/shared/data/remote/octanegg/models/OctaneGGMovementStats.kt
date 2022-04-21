package com.adammcneilly.pocketleague.shared.data.remote.octanegg.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Detailed statistics about the movement for a given player or team.
 */
@Serializable
data class OctaneGGMovementStats(
    @SerialName("avgPowerslideDuration")
    val avgPowerslideDuration: Double? = null,
    @SerialName("avgSpeed")
    val avgSpeed: Double? = null,
    @SerialName("avgSpeedPercentage")
    val avgSpeedPercentage: Double? = null,
    @SerialName("countPowerslide")
    val countPowerslide: Double? = null,
    @SerialName("percentBoostSpeed")
    val percentBoostSpeed: Double? = null,
    @SerialName("percentGround")
    val percentGround: Double? = null,
    @SerialName("percentHighAir")
    val percentHighAir: Double? = null,
    @SerialName("percentLowAir")
    val percentLowAir: Double? = null,
    @SerialName("percentSlowSpeed")
    val percentSlowSpeed: Double? = null,
    @SerialName("percentSupersonicSpeed")
    val percentSupersonicSpeed: Double? = null,
    @SerialName("timeBoostSpeed")
    val timeBoostSpeed: Double? = null,
    @SerialName("timeGround")
    val timeGround: Double? = null,
    @SerialName("timeHighAir")
    val timeHighAir: Double? = null,
    @SerialName("timeLowAir")
    val timeLowAir: Double? = null,
    @SerialName("timePowerslide")
    val timePowerslide: Double? = null,
    @SerialName("timeSlowSpeed")
    val timeSlowSpeed: Double? = null,
    @SerialName("timeSupersonicSpeed")
    val timeSupersonicSpeed: Double? = null,
    @SerialName("totalDistance")
    val totalDistance: Double? = null
)
