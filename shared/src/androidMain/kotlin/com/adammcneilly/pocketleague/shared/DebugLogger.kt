package com.adammcneilly.pocketleague.shared

/**
 * An implementation of a logger for Android that prints to the console.
 */
actual class DebugLogger actual constructor(tagString: String) {
    actual val tag = tagString

    /**
     * Log a [message] for debugging.
     */
    actual fun log(message: String) {
        println("$tag: $message")
    }
}
