package com.adammcneilly.pocketleague.core.datetime

/**
 * Creates a [JVMDateTimeFormatter] to be used on the Android platform.
 *
 * This should be moved into a JVM model another time.
 */
actual fun dateTimeFormatter(): DateTimeFormatter {
    return JVMDateTimeFormatter()
}
