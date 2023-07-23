package com.adammcneilly.pocketleague.data.local.sqldelight.mappers

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.EventStage
import com.adammcneilly.pocketleague.sqldelight.LocalEventStage

fun EventStage.toLocalEventStage(
    eventId: Event.Id,
): LocalEventStage {
    return LocalEventStage(
        id = this.id.id,
        eventId = eventId.id,
        name = this.name,
        region = this.region,
        startDateUTC = this.startDateUTC,
        endDateUTC = this.endDateUTC,
        liquipedia = this.liquipedia,
        qualifier = this.qualifier,
        lan = this.lan,
    )
}

fun LocalEventStage.toEventStage(): EventStage {
    return EventStage(
        id = EventStage.Id(this.id),
        name = this.name,
        region = this.region,
        startDateUTC = this.startDateUTC,
        endDateUTC = this.endDateUTC,
        liquipedia = this.liquipedia,
        qualifier = this.qualifier,
        lan = this.lan,
        location = null,
    )
}
