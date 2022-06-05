package com.adammcneilly.pocketleague.core.datetime

/**
 * Creates an [IosDateTimeFormatter] to be used on the iOS platform.
 */
actual fun dateTimeFormatter(): DateTimeFormatter {
    return IosDateTimeFormatter()
}
