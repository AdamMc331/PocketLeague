package com.adammcneilly.pocketleague.core.datetime

/**
 * Creates an [AndroidDateTimeFormatter] to be used on the Android platform.
 */
actual fun dateTimeFormatter(): DateTimeFormatter {
    return AndroidDateTimeFormatter()
}
