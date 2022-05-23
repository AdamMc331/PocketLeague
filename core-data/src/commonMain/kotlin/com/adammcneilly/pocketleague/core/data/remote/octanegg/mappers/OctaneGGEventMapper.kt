package com.adammcneilly.pocketleague.core.data.remote.octanegg.mappers

import com.adammcneilly.pocketleague.core.data.remote.octanegg.models.OctaneGGEvent
import com.adammcneilly.pocketleague.core.data.remote.octanegg.models.OctaneGGStage
import com.adammcneilly.pocketleague.core.models.Event
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

/**
 * Converts an [OctaneGGEvent] entity into an [Event] within the Pocket League domain.
 */
fun OctaneGGEvent.toEvent(): Event {
    return Event(
        id = this.id ?: "ID Not Available",
        name = this.name ?: "Name Not Available",
        startDate = this.startDate?.let {
            Instant.parse(it)
        }?.toLocalDateTime(TimeZone.UTC),
        endDate = this.endDate?.let {
            Instant.parse(it)
        }?.toLocalDateTime(TimeZone.UTC),
        imageUrl = this.image,
        stages = this.stages?.map(OctaneGGStage::toEventStage).orEmpty(),
        tier = this.tier.orEmpty(),
        region = this.region.orEmpty(),
        mode = this.mode.toString(),
        lan = this.lan == true,
    )
}
