package com.adammcneilly.pocketleague.core.datetime

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toInstant

/**
 * Creates an implementation of [DateTimeFormatter] for a specific platform.
 */
expect fun dateTimeFormatter(): DateTimeFormatter

/**
 * A shared class between platforms that is used to format a date into a user friendly string.
 */
interface DateTimeFormatter {
    /**
     * Given an [instant], treat it as a [LocalDateTime] and convert it into
     * a user friendly string matching the supplied [formatPattern]. We'll format it using
     * the supplied [timeZone] as well.
     */
    fun formatUTCString(
        utcString: String,
        formatPattern: String,
        timeZone: TimeZone,
    ): String?

    /**
     * Given a number of [extraSeconds] that occurred in a game, convert it to a string
     * that represents the extra time spent in a game.
     */
    @Suppress("ImplicitDefaultLocale")
    fun formatExtraTime(
        extraSeconds: Int,
    ): String {
        val minutes = extraSeconds / SECONDS_PER_MINUTE
        val seconds = extraSeconds % SECONDS_PER_MINUTE
        val minutesStr = minutes.toString()
        val secondsStr = seconds.toString().padStart(2, '0')
        return "$minutesStr:$secondsStr"
    }

    /**
     * Determines if the date represented by the supplied [utcString]
     * occurred before the current date.
     */
    fun isBeforeNow(
        utcString: String,
        timeProvider: TimeProvider,
    ): Boolean {
        return utcString < timeProvider.now()
    }

    /**
     * Given a [utcString], convert it to a relative timestamp such as 5m ago, or 5d ago.
     *
     * @return Only returns a relative timestamp if this date is in the past,
     * otherwise null.
     */
    fun getRelativeTimestamp(
        utcString: String,
        timeProvider: TimeProvider,
    ): String? {
        if (!isBeforeNow(utcString, timeProvider)) {
            return null
        }

        val instant = Instant.parse(utcString)

        val now = timeProvider.now().toInstant()

        val duration = now.minus(instant)

        return when {
            duration.inWholeMinutes < MINUTES_PER_HOUR -> {
                "${duration.inWholeMinutes}m ago"
            }
            duration.inWholeHours < HOURS_PER_DAY -> {
                "${duration.inWholeHours}h ago"
            }
            else -> {
                "${duration.inWholeDays}d ago"
            }
        }
    }

    companion object {
        private const val MINUTES_PER_HOUR = 60
        private const val HOURS_PER_DAY = 24
        private const val SECONDS_PER_MINUTE = 60
    }
}
