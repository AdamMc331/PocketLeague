package com.adammcneilly.pocketleague.shared.data.remote.octanegg.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Statistics about boost management for a player or team.
 */
@Serializable
data class OctaneGGBoostStats(
    @SerialName("amountCollected")
    val amountCollected: Int? = null,
    @SerialName("amountCollectedBig")
    val amountCollectedBig: Int? = null,
    @SerialName("amountCollectedSmall")
    val amountCollectedSmall: Int? = null,
    @SerialName("amountOverfill")
    val amountOverfill: Int? = null,
    @SerialName("amountOverfillStolen")
    val amountOverfillStolen: Int? = null,
    @SerialName("amountStolen")
    val amountStolen: Int? = null,
    @SerialName("amountStolenBig")
    val amountStolenBig: Int? = null,
    @SerialName("amountStolenSmall")
    val amountStolenSmall: Int? = null,
    @SerialName("amountUsedWhileSupersonic")
    val amountUsedWhileSupersonic: Int? = null,
    @SerialName("avgAmount")
    val avgAmount: Double? = null,
    @SerialName("bcpm")
    val bcpm: Double? = null,
    @SerialName("bpm")
    val bpm: Int? = null,
    @SerialName("countCollectedBig")
    val countCollectedBig: Int? = null,
    @SerialName("countCollectedSmall")
    val countCollectedSmall: Int? = null,
    @SerialName("countStolenBig")
    val countStolenBig: Int? = null,
    @SerialName("countStolenSmall")
    val countStolenSmall: Int? = null,
    @SerialName("percentBoost0To25")
    val percentBoost0To25: Double? = null,
    @SerialName("percentBoost25To50")
    val percentBoost25To50: Double? = null,
    @SerialName("percentBoost50To75")
    val percentBoost50To75: Double? = null,
    @SerialName("percentBoost75To100")
    val percentBoost75To100: Double? = null,
    @SerialName("percentFullBoost")
    val percentFullBoost: Double? = null,
    @SerialName("percentZeroBoost")
    val percentZeroBoost: Double? = null,
    @SerialName("timeBoost0To25")
    val timeBoost0To25: Double? = null,
    @SerialName("timeBoost25To50")
    val timeBoost25To50: Double? = null,
    @SerialName("timeBoost50To75")
    val timeBoost50To75: Double? = null,
    @SerialName("timeBoost75To100")
    val timeBoost75To100: Double? = null,
    @SerialName("timeFullBoost")
    val timeFullBoost: Double? = null,
    @SerialName("timeZeroBoost")
    val timeZeroBoost: Double? = null
)
