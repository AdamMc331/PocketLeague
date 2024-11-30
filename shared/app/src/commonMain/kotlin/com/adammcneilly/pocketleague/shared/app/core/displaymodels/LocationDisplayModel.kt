package com.adammcneilly.pocketleague.shared.app.core.displaymodels

import com.adammcneilly.pocketleague.shared.app.core.locale.LocaleHelper
import com.adammcneilly.pocketleague.shared.app.core.models.Location

/**
 * Represents the location of an in person event in a user friendly way.
 */
data class LocationDisplayModel(
    val venue: String,
    val cityCountry: String,
) {
    constructor(
        location: Location,
        localeHelper: LocaleHelper,
    ) : this(
        venue = location.venue,
        cityCountry = "${location.city}, ${location.countryName(localeHelper)}",
    )
}

private fun Location.countryName(
    localeHelper: LocaleHelper,
): String {
    return localeHelper.getCountryDisplayName(this.countryCode)
}
