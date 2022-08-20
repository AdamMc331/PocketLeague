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

    fun isBeforeToday(utcString: String): Boolean {
        val instant = Instant.parse(utcString)

        return instant < Clock.System.now()
    }

    /**
     * Given an [instant], convert it to a relative timestamp such as 5m ago, or 5d ago.
     */
    fun getRelativeTimestamp(utcString: String): String {
        val instant = Instant.parse(utcString)

        val now = Clock.System.now()

        val duration = now.minus(instant)

        return when {
            duration.inWholeMinutes < MINUTES_IN_HOUR -> {
                "${duration.inWholeDays}m ago"
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
