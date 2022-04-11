package com.adammcneilly.pocketleague.event.implementation.octanegg.mappers

import com.adammcneilly.pocketleague.core.models.EventSummary
import com.adammcneilly.pocketleague.event.implementation.octanegg.dtos.EventDTO
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone

/**
 * Converts an [EventDTO] from octane.gg into an [EventSummary].
 */
fun EventDTO.toEventSummary(): EventSummary {
    return EventSummary(
        id = this._id,
        eventName = this.name,
        tournamentName = this.name,
        tournamentImageUrl = this.image,
        startDate = LocalDateTime.parse(this.startDate),
        timeZone = TimeZone.UTC,
        numEntrants = null,
        isOnline = false,
    )
}
