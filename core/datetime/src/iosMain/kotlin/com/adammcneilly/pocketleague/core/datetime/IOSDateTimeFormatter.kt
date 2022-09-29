package com.adammcneilly.pocketleague.core.datetime

import kotlinx.datetime.TimeZone

/**
 * Coming soon!
 */
class IOSDateTimeFormatter : DateTimeFormatter {

    override fun formatUTCString(
        utcString: String,
        formatPattern: String,
        timeZone: TimeZone,
    ): String? {
        return utcString
    }
}
