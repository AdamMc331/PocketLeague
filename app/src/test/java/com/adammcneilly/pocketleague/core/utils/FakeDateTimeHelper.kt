package com.adammcneilly.pocketleague.core.utils

import java.time.LocalDateTime

class FakeDateTimeHelper : DateTimeHelper {
    private val seriesDayTimeStringResults: MutableMap<LocalDateTime, String> = mutableMapOf()
    private val eventDayStringResults: MutableMap<Long, String> = mutableMapOf()

    fun mockSeriesDayTimeStringForDate(date: LocalDateTime, result: String) {
        seriesDayTimeStringResults[date] = result
    }

    fun mockEventDayStringForDate(epochSeconds: Long, result: String) {
        eventDayStringResults[epochSeconds] = result
    }

    override fun getSeriesDayTimeString(date: LocalDateTime): String {
        return seriesDayTimeStringResults[date].toString()
    }

    override fun getEventDayString(epochSeconds: Long): String {
        return eventDayStringResults[epochSeconds].toString()
    }
}
