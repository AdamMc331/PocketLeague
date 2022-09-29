package com.adammcneilly.pocketleague.core.datetime

/**
 * Return a [DateTimeFormatter] instance for this platform.
 */
actual fun dateTimeFormatter(): DateTimeFormatter {
    return IOSDateTimeFormatter()
}
