package com.adammcneilly.pocketleague.core.datetime

import kotlinx.datetime.Instant
import kotlinx.datetime.toNSDate
import platform.Foundation.NSDateFormatter

/**
 * Formats dates for the iOS platform.
 */
class IOSDateTimeFormatter : DateTimeFormatter {

    /**
     * Inspiration: https://github.com/Kotlin/kotlinx-datetime/issues/211#issuecomment-1285745207
     *
     * NOTE: This function does nothing with timeZone, it might not work
     * right on iOS?
     */
    override fun formatUTCString(
        utcString: String,
        formatPattern: String,
        timeZone: TimeZone,
    ): String {
        val instant = Instant.parse(utcString)
        val dateFormatter = NSDateFormatter()
        dateFormatter.dateFormat = formatPattern

        return dateFormatter.stringFromDate(instant.toNSDate())
    }
}
