package com.adammcneilly.pocketleague.shared

/**
 * Class used to manage events that can occur in our application to update state.
 *
 * They will typically be run using [screenCoroutine] and if necessary can make data requests through
 * the [dataRepository].
 */
class Events(
    val stateManager: StateManager
) {

    val dataRepository
        get() = stateManager.dataRepository

    /**
     * Runs the supplied [block] inside of a coroutine associated with the scope for our screen.
     */
    fun screenCoroutine(block: suspend () -> Unit) {
        debugLogger.log("/" + stateManager.currentScreenIdentifier.uri + ": an Event is called")
        stateManager.runInScreenScope { block() }
    }
}
