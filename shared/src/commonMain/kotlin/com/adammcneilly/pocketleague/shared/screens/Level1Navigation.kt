package com.adammcneilly.pocketleague.shared.screens

/**
 * Defines a collection of [screenIdentifier] entities that will show up as our level 1 navigation options.
 *
 * In the application, this basically maps to the tabs that appear on the bottom menu.
 */
enum class Level1Navigation(
    val screenIdentifier: ScreenIdentifier,
    val rememberVerticalStack: Boolean = false
) {
    Feed(
        screenIdentifier = ScreenIdentifier.get(screen = Screens.Feed, params = null)
    ),
    MyTeams(
        screenIdentifier = ScreenIdentifier.get(screen = Screens.MyTeams, params = null)
    ),
    Stats(
        screenIdentifier = ScreenIdentifier.get(screen = Screens.Stats, params = null)
    ),
    Records(
        screenIdentifier = ScreenIdentifier.get(screen = Screens.Records, params = null)
    )
}
