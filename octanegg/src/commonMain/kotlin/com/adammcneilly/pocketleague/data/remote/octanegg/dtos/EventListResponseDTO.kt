package com.adammcneilly.pocketleague.data.remote.octanegg.dtos

import kotlinx.serialization.Serializable

/**
 * A DTO that maps to an event list response from the octane.gg API.
 */
@Serializable
data class EventListResponseDTO(
    val events: List<EventDTO>,
    val page: Int,
    val perPage: Int,
    val pageSize: Int,
)
