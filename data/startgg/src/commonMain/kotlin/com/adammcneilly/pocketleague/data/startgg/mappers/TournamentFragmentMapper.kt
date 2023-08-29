package com.adammcneilly.pocketleague.data.startgg.mappers

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.EventRegion
import com.adammcneilly.pocketleague.core.models.EventTier
import com.adammcneilly.pocketleague.data.startgg.fragment.TournamentFragment

/**
 * Converts a [TournamentFragment] GQL model into an [Event] within the Pocket League domain.
 */
fun TournamentFragment.toEvent(): Event {
    val startUtc = (this.startAt as? Int)?.let { startAt ->
        // TODO: Move this into shared module
        // Instant.fromEpochSeconds(startAt.toLong()).toString()
        null
    }

    val endUtc = (this.endAt as? Int)?.let { endAt ->
        // TODO: Move this into a shared module
        // Instant.fromEpochSeconds(endAt.toLong()).toString()
        null
    }

    return Event(
        id = Event.Id(this.id.orEmpty()),
        name = this.name.orEmpty(),
        startDateUTC = startUtc,
        endDateUTC = endUtc,
        imageURL = this.images?.firstOrNull()?.url,
        stages = this.events?.mapNotNull {
            it?.eventFragment?.toEventStage()
        }.orEmpty(),
        lan = this.hasOfflineEvents == true,
        // Start API doesn't have this information, so can we remove it, or make it null, or something else?
        tier = EventTier.Unknown,
        mode = "",
        region = EventRegion.Unknown,
        prize = null,
    )
}
