package com.adammcneilly.pocketleague.core.data.remote.octanegg.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Detailed stats about the positioning of a player or team.
 */
@Serializable
data class OctaneGGPositioningStats(
    @SerialName("avgDistanceToBall")
    val avgDistanceToBall: Double? = null,
    @SerialName("avgDistanceToBallNoPossession")
    val avgDistanceToBallNoPossession: Double? = null,
    @SerialName("avgDistanceToBallPossession")
    val avgDistanceToBallPossession: Double? = null,
    @SerialName("avgDistanceToMates")
    val avgDistanceToMates: Double? = null,
    @SerialName("goalsAgainstWhileLastDefender")
    val goalsAgainstWhileLastDefender: Double? = null,
    @SerialName("percentBehindBall")
    val percentBehindBall: Double? = null,
    @SerialName("percentClosestToBall")
    val percentClosestToBall: Double? = null,
    @SerialName("percentDefensiveHalf")
    val percentDefensiveHalf: Double? = null,
    @SerialName("percentDefensiveThird")
    val percentDefensiveThird: Double? = null,
    @SerialName("percentFarthestFromBall")
    val percentFarthestFromBall: Double? = null,
    @SerialName("percentInfrontBall")
    val percentInfrontBall: Double? = null,
    @SerialName("percentMostBack")
    val percentMostBack: Double? = null,
    @SerialName("percentMostForward")
    val percentMostForward: Double? = null,
    @SerialName("percentNeutralThird")
    val percentNeutralThird: Double? = null,
    @SerialName("percentOffensiveHalf")
    val percentOffensiveHalf: Double? = null,
    @SerialName("percentOffensiveThird")
    val percentOffensiveThird: Double? = null,
    @SerialName("timeBehindBall")
    val timeBehindBall: Double? = null,
    @SerialName("timeClosestToBall")
    val timeClosestToBall: Double? = null,
    @SerialName("timeDefensiveHalf")
    val timeDefensiveHalf: Double? = null,
    @SerialName("timeDefensiveThird")
    val timeDefensiveThird: Double? = null,
    @SerialName("timeFarthestFromBall")
    val timeFarthestFromBall: Double? = null,
    @SerialName("timeInfrontBall")
    val timeInfrontBall: Double? = null,
    @SerialName("timeMostBack")
    val timeMostBack: Double? = null,
    @SerialName("timeMostForward")
    val timeMostForward: Double? = null,
    @SerialName("timeNeutralThird")
    val timeNeutralThird: Double? = null,
    @SerialName("timeOffensiveHalf")
    val timeOffensiveHalf: Double? = null,
    @SerialName("timeOffensiveThird")
    val timeOffensiveThird: Double? = null
)
