package com.adammcneilly.pocketleague.shared.screens

import com.adammcneilly.pocketleague.shared.screens.feed.initFeed

/**
 * An enumeration of all screens that appear somewhere in our application.
 */
enum class Screens(
    val asString: String,
    val navigationLevel: Int = 1,
    val initSettings: Navigation.(ScreenIdentifier) -> ScreenInitSettings,
    val stackableInstances: Boolean = false,
) {
    Feed(
        asString = "feed",
        navigationLevel = 1,
        initSettings = {
            initFeed()
        },
        stackableInstances = true,
    )
}

// enum class Screen(
//    val asString: String,
//    val navigationLevel : Int = 1,
//    val initSettings: Navigation.(ScreenIdentifier) -> ScreenInitSettings,
//    val stackableInstances : Boolean = false,
// ) {
//    CountriesList("countrieslist", 1, { initCountriesList(it.params()) }, true),
//    CountryDetail("country", 2, { initCountryDetail(it.params()) }),
// }
