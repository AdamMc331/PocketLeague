package com.adammcneilly.pocketleague.data.remote.octanegg.mappers

import com.adammcneilly.pocketleague.core.datetime.DateTimeParser
import com.adammcneilly.pocketleague.core.models.EventSummary
import com.adammcneilly.pocketleague.data.remote.octanegg.dtos.EventDTO
import kotlinx.datetime.TimeZone

/**
 * Converts an [EventDTO] from octane.gg into an [EventSummary].
 */
fun EventDTO.toEventSummary(): EventSummary {
    return EventSummary(
        id = this.id,
        eventName = this.name ?: "N/A",
        tournamentName = this.name ?: "N/A",
        tournamentImageUrl = this.image,
        startDate = DateTimeParser().parseOrToday(this.startDate),
        timeZone = TimeZone.UTC,
        numEntrants = null,
        isOnline = false,
    )
}
