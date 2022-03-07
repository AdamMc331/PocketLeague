package com.adammcneilly.pocketleague.shared

import platform.Foundation.NSLog

/**
 * An implementation of a logger for iOS that logs to the xcode console.
 */
actual class DebugLogger actual constructor(tagString: String) {
    actual val tag = tagString

    /**
     * Logs the supplied [message] to the console.
     */
    actual fun log(message: String) {
        NSLog(tag + ": " + message)
    }
}
