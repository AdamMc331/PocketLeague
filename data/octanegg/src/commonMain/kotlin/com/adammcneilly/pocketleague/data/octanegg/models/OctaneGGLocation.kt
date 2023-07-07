package com.adammcneilly.pocketleague.data.octanegg.models

import com.adammcneilly.pocketleague.core.models.Location
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A data class mapping to a location from the octane.gg API.
 */
@Serializable
data class OctaneGGLocation(
    @SerialName("city")
    val city: String? = null,
    @SerialName("country")
    val countryCode: String? = null,
    @SerialName("venue")
    val venue: String? = null,
)

/**
 * Converts a location from the octane.gg domain to ours.
 */
fun OctaneGGLocation.toLocation(): Location {
    return Location(
        venue = this.venue.orEmpty(),
        city = this.city.orEmpty(),
        countryCode = this.countryCode.orEmpty(),
    )
}
