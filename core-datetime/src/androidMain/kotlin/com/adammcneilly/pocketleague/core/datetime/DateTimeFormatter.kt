package com.adammcneilly.pocketleague.core.datetime

actual fun dateTimeFormatter(): DateTimeFormatter {
    return JvmDateTimeFormatter()
}
