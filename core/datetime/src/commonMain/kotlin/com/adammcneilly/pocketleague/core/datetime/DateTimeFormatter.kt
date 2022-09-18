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
        timeZone: TimeZone,
    ): String?
}
