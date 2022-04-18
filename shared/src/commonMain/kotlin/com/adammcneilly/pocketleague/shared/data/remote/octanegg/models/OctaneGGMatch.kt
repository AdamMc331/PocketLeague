package com.adammcneilly.pocketleague.shared.data.remote.octanegg.models

import kotlinx.serialization.SerialName

/**
 * Represents a match between two teams, [blue] and [orange].
 */
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
