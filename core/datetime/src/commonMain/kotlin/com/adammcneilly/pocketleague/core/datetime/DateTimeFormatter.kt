package com.adammcneilly.pocketleague.core.datetime

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone

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
        timeZone: TimeZone
    ): String?

    /**
     * Given a number of [extraSeconds] that occurred in a game, convert it to a string
     * that represents the extra time spent in a game.
     */
    @Suppress("ImplicitDefaultLocale")
    fun formatExtraTime(
        extraSeconds: Int
    ): String {
        val minutes = extraSeconds / SECONDS_PER_MINUTE
        val seconds = extraSeconds % SECONDS_PER_MINUTE
        val minutesStr = minutes.toString()
        val secondsStr = seconds.toString().padStart(2, '0')
        return "$minutesStr:$secondsStr"
    }

    companion object {
        const val SECONDS_PER_MINUTE = 60
    }
}
