package com.adammcneilly.pocketleague.shared.screens

import kotlinx.coroutines.flow.StateFlow

/**
 * Root view model for the application that exposes a [stateFlow] of our application's state.
 */
class DKMPViewModel {

    private val stateManager by lazy { StateManager() }

    val stateFlow: StateFlow<AppState>
        get() = stateManager.mutableStateFlow

    val navigation by lazy { Navigation(stateManager) }

    /**
     * Factory methods are defined in the platform-specific domains.
     */
    companion object Factory
}
