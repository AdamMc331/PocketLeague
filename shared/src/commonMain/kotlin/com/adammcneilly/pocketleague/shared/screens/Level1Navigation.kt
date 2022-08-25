package com.adammcneilly.pocketleague.shared.screens

import com.adammcneilly.pocketleague.feature.core.ScreenIdentifier
import com.adammcneilly.pocketleague.shared.screens.records.RecordsScreen
import com.adammcneilly.pocketleague.shared.screens.stats.StatsScreen

/**
 * Defines a collection of [screenIdentifier] entities that will show up as our level 1 navigation options.
 *
 * In the application, this basically maps to the tabs that appear on the bottom menu.
 */
enum class Level1Navigation(
    val getScreenIdentifier: (StateManager) -> ScreenIdentifier,
    val rememberVerticalStack: Boolean = false,
) {
    Feed(
        getScreenIdentifier = {
            com.adammcneilly.pocketleague.feature.core.ScreenIdentifier.get(
                screen = it.getFeedScreen(),
                params = null,
            )
        },
    ),
    Stats(
        getScreenIdentifier = {
            ScreenIdentifier.get(
                screen = StatsScreen,
                params = null,
            )
        },
    ),
    Records(
        getScreenIdentifier = {
            ScreenIdentifier.get(
                screen = RecordsScreen,
                params = null,
            )
        },
    ),
}
