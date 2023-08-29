package com.adammcneilly.pocketleague.core.datetime

typealias KotlinTimeZone = kotlinx.datetime.TimeZone

/**
 * An enumeration of possible timezones used in the application.
 *
 * Ideally, we either want [SYSTEM_DEFAULT], but potentially [UTC].
 */
enum class TimeZone {
    UTC,
    SYSTEM_DEFAULT,
    ;

    fun toKotlinTimeZone(): KotlinTimeZone {
        return when (this) {
            UTC -> KotlinTimeZone.UTC
            SYSTEM_DEFAULT -> KotlinTimeZone.currentSystemDefault()
        }
    }
}
