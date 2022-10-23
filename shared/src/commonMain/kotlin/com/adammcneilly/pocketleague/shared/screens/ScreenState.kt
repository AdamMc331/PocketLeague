package com.adammcneilly.pocketleague.shared.screens

/**
 * An empty interface used to provide type safety for the current UI state of any screen in the application.
 */
interface ScreenState {
    /**
     * If necessary, a screen state can override the title that should appear at the top of the screen.
     */
    val title: String?
}
