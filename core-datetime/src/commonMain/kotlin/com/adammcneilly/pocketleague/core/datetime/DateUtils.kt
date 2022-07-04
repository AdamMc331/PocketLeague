package com.adammcneilly.pocketleague.core.datetime

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

/**
 * Utility methods for a date that don't have to be resolved in a platform specific way.
 *
 * TODO: Consider if we can move this inside our DateTimeFormatter interface?
 */
object DateUtils {
    private const val MINUTES_IN_HOUR = 60
    private const val HOURS_IN_DAY = 24

    fun getRelativeTimestamp(instant: Instant): String {
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
