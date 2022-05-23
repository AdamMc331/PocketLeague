package com.adammcneilly.pocketleague.core.data.remote.octanegg.mappers

import com.adammcneilly.pocketleague.core.data.remote.octanegg.models.OctaneGGStage
import com.adammcneilly.pocketleague.core.models.EventStage
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

/**
 * Converts an [OctaneGGStage] to an [EventStage].
 */
fun OctaneGGStage.toEventStage(): EventStage {
    return EventStage(
        id = this.id.toString(),
        name = this.name.orEmpty(),
        region = this.region.orEmpty(),
        startDate = this.startDate?.let {
            Instant.parse(it)
        }?.toLocalDateTime(TimeZone.UTC),
        endDate = this.endDate?.let {
            Instant.parse(it)
        }?.toLocalDateTime(TimeZone.UTC),
        liquipedia = this.liquipedia.orEmpty(),
        qualifier = this.qualifier == true,
        lan = this.lan == true,
    )
}
