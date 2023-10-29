package com.adammcneilly.pocketleague.data.startgg.mappers

import com.adammcneilly.pocketleague.core.datetime.TimeProvider
import com.adammcneilly.pocketleague.core.models.EventStage
import com.adammcneilly.pocketleague.data.startgg.fragment.EventFragment

/**
 * Convert an [EventFragment] GQL model into an [EventStage] from the pocket league domain.
 */
fun EventFragment.toEventStage(timeProvider: TimeProvider): EventStage {
    val startDateUtc =
        (this.startAt as? Int)?.let { startAt ->
            timeProvider.fromEpochSeconds(startAt.toLong())
        }

    return EventStage(
        id = EventStage.Id(this.id.orEmpty()),
        name = this.name.orEmpty(),
        region = "",
        startDateUTC = startDateUtc,
        lan = (isOnline == false),
        // Start API doesn't have this information, so we can either remove it, or maybe we should
        // make all of these null to show empty data.
        endDateUTC = null,
        liquipedia = "",
        qualifier = false,
        location = null,
    )
}
