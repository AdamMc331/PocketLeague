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
        Instant.fromEpochSeconds(startAt.toLong()).toString()
    }

    val endUtc = (this.endAt as? Int)?.let { endAt ->
        Instant.fromEpochSeconds(endAt.toLong()).toString()
    }

    return Event(
        id = this.id.orEmpty(),
        name = this.name.orEmpty(),
        startDateUTC = startUtc,
        endDateUTC = endUtc,
        imageURL = this.images?.firstOrNull()?.url,
        stages = this.events?.mapNotNull {
            it?.eventFragment?.toEventStage()
        }.orEmpty(),
        lan = this.hasOfflineEvents == true,
        // Start API doesn't have this information, so we just return defaults. Ideally, further up
        // the data pipeline in this project, we'll find a way to populate this information.
        tier = EventTier.Unknown,
        mode = "",
        region = EventRegion.Unknown,
        prize = null,
    )
}
