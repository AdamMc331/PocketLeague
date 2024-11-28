package com.adammcneilly.pocketleague.shared.app.core.datetime

import kotlinx.datetime.Instant
import kotlin.time.Duration.Companion.days

/**
 * Similar to a kotlinx Clock instance, this time provider allows a platform
 * to specify [now], without any dependencies on a particular datetime library.
 */
interface TimeProvider {
    /**
     * Return a string representation (in ISO format) of the current time.
     */
    fun now(): String

    /**
     * Return a string representation of [now] minus [numDays].
     */
    fun daysAgo(
        numDays: Int,
    ): String {
        return Instant.parse(now()).minus(numDays.days).toString()
    }

    /**
     * Given [epochSeconds], convert it to an ISO format string.
     */
    fun fromEpochSeconds(
        epochSeconds: Long,
    ): String {
        return Instant.fromEpochSeconds(epochSeconds).toString()
    }
}
