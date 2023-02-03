package com.adammcneilly.pocketleague.data.octanegg.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A list of [participants] within some event.
 */
@Serializable
data class OctaneGGEventParticipants(
    @SerialName("participants")
    val participants: List<OctaneGGTeamDetail>
)
