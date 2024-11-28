package com.adammcneilly.pocketleague.shared.app.core.datetime

import kotlinx.datetime.Instant
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toLocalDateTime

/**
 * Creates a [DateTimeFormatter] to be used on the Android platform.
 */
actual fun dateTimeFormatter(): DateTimeFormatter {
    return AndroidDateTimeFormatter()
}

private class AndroidDateTimeFormatter : DateTimeFormatter {
    /**
     * See commonMain documentation.
     */
    override fun formatUTCString(
        utcString: String,
        formatPattern: String,
        timeZone: TimeZone,
    ): String? {
        val instant = Instant.parse(utcString)

        val localDateTime = instant.toLocalDateTime(timeZone.toKotlinTimeZone())

        val dateTimeFormatter = java.time.format.DateTimeFormatter.ofPattern(formatPattern)

        return localDateTime.toJavaLocalDateTime().format(dateTimeFormatter)
    }
}
