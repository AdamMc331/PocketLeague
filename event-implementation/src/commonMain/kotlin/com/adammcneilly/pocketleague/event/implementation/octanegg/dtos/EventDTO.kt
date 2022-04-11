package com.adammcneilly.pocketleague.event.implementation.octanegg.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A data class mapping to an Event from the octane.gg API.
 */
@Serializable
data class EventDTO(
    @SerialName("_id")
    val id: String,
    val endDate: String? = null,
    val image: String? = null,
    val mode: Int? = null,
    val name: String? = null,
    val prize: PrizeDTO? = null,
    val region: String? = null,
    val slug: String? = null,
    val stages: List<StageDTO>? = null,
    val startDate: String? = null,
    val tier: String? = null,
    val groups: List<String>? = null,
)
