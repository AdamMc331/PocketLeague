package com.adammcneilly.pocketleague.shared.screens

import com.adammcneilly.pocketleague.core.feature.ScreenState

/**
 * This class provides a way to get the [ScreenState] of a specific screen as needed.
 */
class StateProvider(val stateManager: StateManager) {
    /**
     * Pulls the current [ScreenState] from the given [screenIdentifier].
     */
    inline fun <reified T : ScreenState> get(screenIdentifier: ScreenIdentifier): T {
        return stateManager.screenStatesMap[screenIdentifier.uri] as T
    }

    /**
     * Reified functions cannot be exported to iOS, so we use this function
     * to return the [ScreenState] interface type, which we then need to cast to the
     * specific state class.
     */
    fun getToCast(screenIdentifier: ScreenIdentifier): ScreenState? {
        return stateManager.screenStatesMap[screenIdentifier.uri]
    }
}
