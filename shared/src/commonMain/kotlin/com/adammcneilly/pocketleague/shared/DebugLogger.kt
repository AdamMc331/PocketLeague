package com.adammcneilly.pocketleague.shared

/**
 * An expected logging implementation for each platforms to help developers with debugging.
 */
expect class DebugLogger(tagString: String) {
    val tag: String

    /**
     * Log a message to the relevant console for the developer.
     */
    fun log(message: String)
}
