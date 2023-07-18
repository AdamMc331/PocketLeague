package com.adammcneilly.pocketleague.core.datetime

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

/**
 * Utility methods for a date that don't have to be resolved in a platform specific way.
 *
 * NOTE: Consider if we can move this inside our DateTimeFormatter interface?
 */
object DateUtils {
    private const val MINUTES_IN_HOUR = 60
    private const val HOURS_IN_DAY = 24

    /**
     * Determines if the date represented by the supplied [utcString]
     * occurred before the current date.
     */
    fun isBeforeNow(
        utcString: String,
        clock: Clock = Clock.System,
    ): Boolean {
        val instant = Instant.parse(utcString)

        return instant < clock.now()
    }

    /**
     * Given a [utcString], convert it to a relative timestamp such as 5m ago, or 5d ago.
     */
    fun getRelativeTimestamp(
        utcString: String,
        clock: Clock = Clock.System,
    ): String {
        require(isBeforeNow(utcString, clock)) {
            "getRelativeTimestamp only valid for past dates."
        }

        val instant = Instant.parse(utcString)

        val now = clock.now()

        val duration = now.minus(instant)

        return when {
            duration.inWholeMinutes < MINUTES_IN_HOUR -> {
                "${duration.inWholeMinutes}m ago"
            }
            duration.inWholeHours < HOURS_IN_DAY -> {
                "${duration.inWholeHours}h ago"
            }
            else -> {
                "${duration.inWholeDays}d ago"
            }
        }
    }
}
