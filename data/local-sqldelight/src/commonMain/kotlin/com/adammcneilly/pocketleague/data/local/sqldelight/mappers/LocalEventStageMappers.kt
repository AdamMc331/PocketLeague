package com.adammcneilly.pocketleague.data.local.sqldelight.mappers

import com.adammcneilly.pocketleague.core.models.EventStage
import com.adammcneilly.pocketleague.sqldelight.LocalEventStage

fun EventStage.toLocalEventStage(
    eventId: String
): LocalEventStage {
    return LocalEventStage(
        id = this.id,
        eventId = eventId,
        name = this.name,
        region = this.region,
        startDateUTC = this.startDateUTC,
        endDateUTC = this.endDateUTC,
        liquipedia = this.liquipedia,
        qualifier = this.qualifier,
        lan = this.lan
    )
}

fun LocalEventStage.toEventStage(): EventStage {
    return EventStage(
        id = this.id,
        name = this.name,
        region = this.region,
        startDateUTC = this.startDateUTC,
        endDateUTC = this.endDateUTC,
        liquipedia = this.liquipedia,
        qualifier = this.qualifier,
        lan = this.lan
    )
}
