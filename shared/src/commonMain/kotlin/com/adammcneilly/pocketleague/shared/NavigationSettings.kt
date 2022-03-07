package com.adammcneilly.pocketleague.shared

/**
 * Define the settings used to control navigation within our application.
 *
 * @property[homeScreen] Defines the start screen to render.
 */
object NavigationSettings {
    val homeScreen = Level1Navigation.EventSummaries
    const val saveLastLevel1Screen = true
    const val alwaysQuitOnHomeScreen = true
}

/**
 * Defines all screens that appear on the root level navigation of our application.
 */
enum class Level1Navigation(
    val screenIdentifier: ScreenIdentifier,
    val rememberVerticalStack: Boolean = false
) {
    EventSummaries(ScreenIdentifier.get(Screens.EventSummaryList, null))
}
