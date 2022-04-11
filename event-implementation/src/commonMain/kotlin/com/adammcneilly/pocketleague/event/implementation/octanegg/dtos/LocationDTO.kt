package com.adammcneilly.pocketleague.event.implementation.octanegg.dtos

import kotlinx.serialization.Serializable

/**
 * A data class mapping to a location from the octane.gg API.
 */
@Serializable
internal data class LocationDTO(
    val city: String? = null,
    val country: String? = null,
    val venue: String? = null,
)
