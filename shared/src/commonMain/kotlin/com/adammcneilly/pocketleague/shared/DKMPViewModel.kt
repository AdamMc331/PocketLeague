package com.adammcneilly.pocketleague.shared

import kotlinx.coroutines.flow.StateFlow
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
val debugLogger by lazy { DebugLogger("D-KMP SAMPLE") }

/**
 * ViewModel to manage the state of our entire application.
 */
class DKMPViewModel(repository: Repository) {

    /**
     * Factory methods are defined inside the platform-specific shared code (androidMain and iosMain).
     */
    companion object Factory

    val stateFlow: StateFlow<AppState>
        get() = stateManager.mutableStateFlow

    private val stateManager by lazy { StateManager(repository) }

    val navigation by lazy { Navigation(stateManager) }
}
