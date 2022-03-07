package com.adammcneilly.pocketleague.shared

// CONFIGURATION SETTINGS

object navigationSettings {
    val homeScreen = Level1Navigation.EventSummaries // the start screen should be specified here
    val saveLastLevel1Screen = true
    val alwaysQuitOnHomeScreen = true
}

// LEVEL 1 NAVIGATION OF THE APP

enum class Level1Navigation(
    val screenIdentifier: ScreenIdentifier,
    val rememberVerticalStack: Boolean = false
) {
    EventSummaries(ScreenIdentifier.get(Screens.EventSummaryList, null))
}
