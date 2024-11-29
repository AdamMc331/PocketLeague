package com.adammcneilly.pocketleague.shared.app.core.displaymodels

import com.adammcneilly.pocketleague.shared.app.core.models.Location

/**
 * Represents the location of an in person event in a user friendly way.
 */
data class LocationDisplayModel(
    val venue: String,
    val cityCountry: String,
) {
    constructor(location: Location) : this(
        venue = location.venue,
        cityCountry = "TODO: Copy Locale Helper",
    )
}

// /**
// * Converts a [Location] to a more user friendly [LocationDisplayModel].
// */
// fun Location.toDisplayModel(
//    localeHelper: LocaleHelper,
// ): LocationDisplayModel {
//    val countryName = localeHelper.getCountryDisplayName(this.countryCode)
//
//    return LocationDisplayModel(
//        venue = this.venue,
//        cityCountry = "${this.city}, $countryName",
//    )
// }
