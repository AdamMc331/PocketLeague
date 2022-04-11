package com.adammcneilly.pocketleague.event.implementation.octanegg.dtos

import kotlinx.serialization.Serializable

/**
 * A data class mapping to a location from the octane.gg API.
 */
@Serializable
data class LocationDTO(
    val city: String,
    val country: String,
    val venue: String? = null,
)
