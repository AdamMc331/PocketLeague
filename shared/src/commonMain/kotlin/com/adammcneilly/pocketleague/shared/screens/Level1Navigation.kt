package com.adammcneilly.pocketleague.shared.screens

import com.adammcneilly.pocketleague.feature.core.Screen
import com.adammcneilly.pocketleague.feature.core.ScreenIdentifier
import com.adammcneilly.pocketleague.shared.screens.records.RecordsScreen
import com.adammcneilly.pocketleague.shared.screens.stats.StatsScreen

/**
 * An enumeration of all screens that are used on our base level 1 navigation within the app. Effectively,
 * this just means any screens that appear in the bottom navigation menu or navigation rail.
 *
 * By using an enum, we can have an exhaustive list of possible screens, as well as a quick way for
 * us to specify which screen we want to navigate to.
 *
 * We should note, though, that some [Screen] entities may require dependencies that can only be
 * provided via our [StateManager]. As a result, each possible [Level1Navigation] will provide a [getScreenIdentifier]
 * function to use our state manager to pull those dependencies and generate our [ScreenIdentifier] entity.
 */
enum class Level1Navigation(
    val getScreenIdentifier: (StateManager) -> ScreenIdentifier,
    val rememberVerticalStack: Boolean = false,
) {
    Feed(
        getScreenIdentifier = {
            ScreenIdentifier.get(
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
