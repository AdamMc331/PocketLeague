package com.adammcneilly.pocketleague.event.implementation.smashgg.mappers

import com.adammcneilly.pocketleague.core.models.EventSummary
import com.adammcneilly.pocketleague.event.implementation.graphql.fragment.EventSummaryFragment
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun EventSummaryFragment.toEvent(): EventSummary {
    val startSeconds = (this.startAt as Int).toLong()
    val eventTimeZone = TimeZone.UTC
    val startDate = Instant.fromEpochSeconds(startSeconds).toLocalDateTime(eventTimeZone)

    return EventSummary(
        id = this.id.orEmpty(),
        eventName = this.name.orEmpty(),
        tournamentName = this.tournament?.name.orEmpty(),
        startDate = startDate,
        timeZone = eventTimeZone,
        numEntrants = this.numEntrants,
        isOnline = this.isOnline == true,
        tournamentImageUrl = this.tournament?.images?.firstOrNull()?.url.orEmpty(),
    )
}
