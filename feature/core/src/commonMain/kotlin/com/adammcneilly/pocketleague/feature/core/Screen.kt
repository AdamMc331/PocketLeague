package com.adammcneilly.pocketleague.feature.core

/**
 * An interface defining the expected behavior of any screen within the application. In this context, a screen
 * likely represents a page taking up the entire phone, or a portion of a larger device.
 */
interface Screen {
    /**
     * A unique string representation of this screen.
     */
    val asString: String

    /**
     * The depth in which this screen appears. 1 being a base level screen, 2 being one step deeper, etc.
     */
    val navigationLevel: Int

    /**
     * A function to be invoked when we want to initialize this screen.
     */
    val initSettings: (ScreenParams?) -> ScreenInitSettings

    /**
     * True if multiple instances of this screen can exist at one time.
     */
    val stackableInstances: Boolean
}
