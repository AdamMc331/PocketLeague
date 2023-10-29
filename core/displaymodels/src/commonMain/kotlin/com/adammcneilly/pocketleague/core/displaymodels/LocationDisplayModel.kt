package com.adammcneilly.pocketleague.core.displaymodels

import com.adammcneilly.pocketleague.core.locale.LocaleHelper
import com.adammcneilly.pocketleague.core.locale.provideLocaleHelper
import com.adammcneilly.pocketleague.core.models.Location

/**
 * Represents the location of an in person event in a user friendly way.
 */
data class LocationDisplayModel(
    val venue: String,
    val cityCountry: String,
)

/**
 * Proxies to [toDisplayModel] with default [LocaleHelper].
 */
fun Location.toDisplayModel(): LocationDisplayModel {
    return this.toDisplayModel(provideLocaleHelper())
}

/**
 * Converts a [Location] to a more user friendly [LocationDisplayModel].
 */
fun Location.toDisplayModel(
    localeHelper: LocaleHelper,
): LocationDisplayModel {
    val countryName = localeHelper.getCountryDisplayName(this.countryCode)

    return LocationDisplayModel(
        venue = this.venue,
        cityCountry = "${this.city}, $countryName",
    )
}
