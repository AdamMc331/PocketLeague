package com.adammcneilly.pocketleague.core.data.remote.octanegg.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a match between two teams, [blue] and [orange].
 */
@Serializable
data class OctaneGGMatch(
    @SerialName("_id")
    val id: String? = null,
    @SerialName("slug")
    val slug: String? = null,
    @SerialName("event")
    val event: OctaneGGEvent? = null,
    @SerialName("date")
    val date: String? = null,
    @SerialName("blue")
    val blue: OctaneGGMatchTeamResult? = null,
    @SerialName("orange")
    val orange: OctaneGGMatchTeamResult? = null,
)
