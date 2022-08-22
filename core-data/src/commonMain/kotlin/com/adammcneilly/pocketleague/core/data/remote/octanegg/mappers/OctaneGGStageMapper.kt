package com.adammcneilly.pocketleague.core.data.remote.octanegg.mappers

import com.adammcneilly.pocketleague.core.data.remote.octanegg.models.OctaneGGStage
import com.adammcneilly.pocketleague.core.models.EventStage

/**
 * Converts an [OctaneGGStage] to an [EventStage].
 */
fun OctaneGGStage.toEventStage(): EventStage {
    return EventStage(
        id = this.id.toString(),
        name = this.name.orEmpty(),
        region = this.region.orEmpty(),
        startDateUTC = this.startDateUTC,
        endDateUTC = this.endDateUTC,
        liquipedia = this.liquipedia.orEmpty(),
        qualifier = this.qualifier == true,
        lan = this.lan == true,
    )
}
