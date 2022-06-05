package com.adammcneilly.pocketleague.core.datetime

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone

/**
 * A shared class between platforms that is used to format a date into a user friendly string.
 */
interface DateTimeFormatter {

    /**
     * Given an [instant], treat it as a [LocalDateTime] and convert it into
     * a user friendly string matching the supplied [formatPattern]. We'll format it using
     * the supplied [timeZone] as well.
     */
    fun formatInstant(
        instant: Instant,
        formatPattern: String,
        timeZone: TimeZone,
    ): String?
}
