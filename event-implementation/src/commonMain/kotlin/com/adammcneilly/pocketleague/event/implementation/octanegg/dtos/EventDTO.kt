package com.adammcneilly.pocketleague.event.implementation.octanegg.dtos

import kotlinx.serialization.Serializable

/**
 * A data class mapping to an Event from the octane.gg API.
 */
@Serializable
data class EventDTO(
    val _id: String,
    val endDate: String,
    val image: String,
    val mode: Int,
    val name: String,
    val prize: PrizeDTO,
    val region: String,
    val slug: String,
    val stages: List<StageDTO>,
    val startDate: String,
    val tier: String,
    val groups: List<String>? = null,
)
