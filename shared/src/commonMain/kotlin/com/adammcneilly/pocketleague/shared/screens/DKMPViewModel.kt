package com.adammcneilly.pocketleague.shared.screens

import com.adammcneilly.pocketleague.shared.data.AppDependencies
import kotlinx.coroutines.flow.StateFlow

/**
 * Root view model for the application that exposes a [stateFlow] of our application's state.
 */
class DKMPViewModel(
    private val dependencies: AppDependencies
) {

    private val stateManager by lazy {
        StateManager(dependencies)
    }

    val stateFlow: StateFlow<AppState>
        get() = stateManager.mutableStateFlow

    val navigation by lazy { Navigation(stateManager) }

    /**
     * Factory methods are defined in the platform-specific domains.
     */
    companion object Factory
}
