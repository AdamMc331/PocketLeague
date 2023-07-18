package com.adammcneilly.pocketleague.core.datetime

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

/**
 * Custom implementation of a [Clock] to hardcode a specific date.
 *
 * Allows us to test the app at any given point in time.
 */
class DebugClock(
    private val dateString: String = "2023-07-08T16:00:00Z",
) : Clock {

    override fun now(): Instant {
        return Instant.parse(dateString)
    }
}
