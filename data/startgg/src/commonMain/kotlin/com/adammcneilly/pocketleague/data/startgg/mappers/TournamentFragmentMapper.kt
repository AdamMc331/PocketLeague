package com.adammcneilly.pocketleague.data.startgg.mappers

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.EventRegion
import com.adammcneilly.pocketleague.core.models.EventTier
import com.adammcneilly.pocketleague.data.startgg.fragment.TournamentFragment
import kotlinx.datetime.Instant

/**
 * Converts a [TournamentFragment] GQL model into an [Event] within the Pocket League domain.
 */
fun TournamentFragment.toEvent(): Event {
    val startUtc = (this.startAt as? Int)?.let { startAt ->
        Instant.fromEpochMilliseconds(startAt.toLong()).toString()
    }

    val endUtc = (this.endAt as? Int)?.let { endAt ->
        Instant.fromEpochMilliseconds(endAt.toLong()).toString()
    }

    return Event(
        id = this.id.orEmpty(),
        name = this.name.orEmpty(),
        startDateUTC = startUtc,
        endDateUTC = endUtc,
        imageURL = this.images?.firstOrNull()?.url,
        stages = emptyList(),
        tier = EventTier.Unknown,
        mode = "",
        region = EventRegion.Unknown,
        lan = this.hasOfflineEvents == true,
        prize = null,
    )
}
