package com.adammcneilly.pocketleague.core.datetime

import kotlinx.datetime.Clock

/**
 * Implementation of [TimeProvider] to provide the current time from the platform's system.
 */
object SystemTimeProvider : TimeProvider {
    override fun now(): String {
        return Clock.System.now().toString()
    }
}
