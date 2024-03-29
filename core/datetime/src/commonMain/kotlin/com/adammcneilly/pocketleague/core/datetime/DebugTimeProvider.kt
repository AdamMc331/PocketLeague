package com.adammcneilly.pocketleague.core.datetime

/**
 * Custom implementation of a [TimeProvider] to hardcode a specific date.
 *
 * Allows us to test the app at any given point in time.
 */
class DebugTimeProvider(
    private val dateString: String = "2023-03-02T16:00:00Z",
) : TimeProvider {
    override fun now(): String {
        return dateString
    }
}
