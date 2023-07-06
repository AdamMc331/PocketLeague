package com.adammcneilly.pocketleague.core.datetime

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toNSDate
import platform.Foundation.NSDateFormatter

/**
 * Coming soon!
 */
class IOSDateTimeFormatter : DateTimeFormatter {

    /**
     * Inspiration: https://github.com/Kotlin/kotlinx-datetime/issues/211#issuecomment-1285745207
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
