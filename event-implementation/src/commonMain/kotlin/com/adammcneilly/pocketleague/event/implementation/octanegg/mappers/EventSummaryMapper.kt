package com.adammcneilly.pocketleague.event.implementation.octanegg.mappers

import com.adammcneilly.pocketleague.core.models.EventSummary
import com.adammcneilly.pocketleague.event.implementation.octanegg.dtos.EventDTO
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

/**
 * Converts an [EventDTO] from octane.gg into an [EventSummary].
 */
fun EventDTO.toEventSummary(): EventSummary {
    return EventSummary(
        id = this._id,
        eventName = this.name,
        tournamentName = this.name,
        tournamentImageUrl = this.image,
        startDate = Instant.parse(this.startDate).toLocalDateTime(TimeZone.UTC),
        timeZone = TimeZone.UTC,
        numEntrants = null,
        isOnline = false,
    )
}
