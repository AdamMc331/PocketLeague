package com.adammcneilly.pocketleague.core.utils

import java.time.LocalDateTime

/**
 * A collection of helper functions for dealing with date and time objects.
 */
interface DateTimeHelper {
    /**
     * Given a [date], format into a user friendly time string for a series between two teams.
     */
    fun getSeriesDayTimeString(date: LocalDateTime): String

    /**
     * Given some [epochSeconds], convert it to a user friendly string to show the day of this event.
     */
    fun getEventDayString(epochSeconds: Long): String
}
