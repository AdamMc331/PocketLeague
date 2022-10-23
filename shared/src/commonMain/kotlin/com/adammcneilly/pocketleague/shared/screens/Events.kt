package com.adammcneilly.pocketleague.shared.screens

import com.adammcneilly.pocketleague.shared.data.Repository
import kotlinx.coroutines.CoroutineScope

/**
 * This class manages all of the event handling for various screens, specifically
 * exposing the [screenCoroutine] function to run asynchronous work.
 */
class Events(
    val stateManager: StateManager,
) {
    val repository: Repository
        get() = stateManager.repository

    /**
     * This will run the supplied [block] inside the coroutine scope of the current
     * screen.
     */
    fun screenCoroutine(block: suspend (CoroutineScope) -> Unit) {
        stateManager.runInScreenScope {
            block(it)
        }
    }
}
