package com.adammcneilly.pocketleague.core.displaymodels

import com.adammcneilly.pocketleague.core.locale.getCountryDisplayName
import com.adammcneilly.pocketleague.core.models.Location

/**
 * Represents the location of an in person event in a user friendly way.
 */
data class LocationDisplayModel(
    val venue: String,
    val cityCountry: String,
)

/**
 * Converts a [Location] to a more user friendly [LocationDisplayModel].
 */
fun Location.toDisplayModel(): LocationDisplayModel {
    val countryName = getCountryDisplayName(this.countryCode)

    return LocationDisplayModel(
        venue = this.venue,
        cityCountry = "${this.city}, $countryName",
    )
}
