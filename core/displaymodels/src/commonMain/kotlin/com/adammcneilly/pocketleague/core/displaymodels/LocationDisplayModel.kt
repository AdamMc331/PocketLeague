package com.adammcneilly.pocketleague.core.displaymodels

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
    // We receive a country code from location (ex: us, de) and here we should convert it to a
    // full name like "United States", or "Germany".
    // Need to find out if there's a Kotlin, or platform specific library, that can help us do
    // that.
    val countryName = this.countryCode

    return LocationDisplayModel(
        venue = this.venue,
        cityCountry = "${this.city}, $countryName",
    )
}
