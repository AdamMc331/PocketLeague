package com.adammcneilly.pocketleague.core.utils

import java.time.LocalDateTime
import java.time.ZonedDateTime

class FakeDateTimeHelper : DateTimeHelper {
    private val seriesDayTimeStringResults: MutableMap<LocalDateTime, String> = mutableMapOf()
    private val eventDayStringResults: MutableMap<ZonedDateTime, String> = mutableMapOf()

    fun mockSeriesDayTimeStringForDate(date: LocalDateTime, result: String) {
        seriesDayTimeStringResults[date] = result
    }

    fun mockEventDayStringForDate(date: ZonedDateTime, result: String) {
        eventDayStringResults[date] = result
    }

    override fun getSeriesDayTimeString(date: LocalDateTime): String {
        return seriesDayTimeStringResults[date].toString()
    }

    override fun getEventDayString(date: ZonedDateTime): String {
        return eventDayStringResults[date].toString()
    }
}
