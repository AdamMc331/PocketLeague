package com.adammcneilly.pocketleague.shared.data.remote.octanegg.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A game played within a [OctaneGGMatch].
 */
@Serializable
data class OctaneGGGame(
    @SerialName("ballchasing")
    val ballchasing: String? = null,
    @SerialName("blue")
    val blue: OctaneGGGameTeamResult? = null,
    @SerialName("date")
    val date: String? = null,
    @SerialName("duration")
    val duration: Int? = null,
    @SerialName("_id")
    val id: String? = null,
    @SerialName("map")
    val map: OctaneGGMap? = null,
    @SerialName("match")
    val match: OctaneGGMatch? = null,
    @SerialName("number")
    val number: Int? = null,
    @SerialName("octane_id")
    val octaneId: String? = null,
    @SerialName("orange")
    val orange: OctaneGGGameTeamResult? = null
)
