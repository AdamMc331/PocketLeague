package com.adammcneilly.pocketleague.shared

/**
 * A class to provide state to a screen through the given [stateManager].
 */
class StateProvider(val stateManager: StateManager) {

    /**
     * Retrieve the state of the screen matching the [screenIdentifier].
     */
    inline fun <reified T : ScreenState> get(screenIdentifier: ScreenIdentifier): T {
        return stateManager.screenStatesMap[screenIdentifier.uri] as T
    }

    /**
     * Reified functions cannot be exported to iOS, so we use this function returning the [ScreenState]
     * interface type on Swift, we then need to cast it to the specific state class.
     */
    fun getToCast(screenIdentifier: ScreenIdentifier): ScreenState? {
        return stateManager.screenStatesMap[screenIdentifier.uri]
    }
}
