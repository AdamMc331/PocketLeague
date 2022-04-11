package com.adammcneilly.pocketleague.event.implementation.octanegg.mappers

import com.adammcneilly.pocketleague.core.models.EventSummary
import com.adammcneilly.pocketleague.event.implementation.octanegg.dtos.EventDTO
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

/**
 * Converts an [EventDTO] from octane.gg into an [EventSummary].
 */
fun EventDTO.toEventSummary(): EventSummary {
    val parsedStartDate = this.startDate?.let {
        Instant.parse(it)
    }?.toLocalDateTime(TimeZone.UTC)

    val today = Clock.System.now().toLocalDateTime(TimeZone.UTC)

    val actualStartDate = parsedStartDate ?: today

    return EventSummary(
        id = this.id,
        eventName = this.name ?: "N/A",
        tournamentName = this.name ?: "N/A",
        tournamentImageUrl = this.image,
        startDate = actualStartDate,
        timeZone = TimeZone.UTC,
        numEntrants = null,
        isOnline = false,
    )
}
