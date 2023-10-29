package com.adammcneilly.pocketleague.data.local.sqldelight.mappers

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.EventStage
import com.adammcneilly.pocketleague.core.models.Location
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
        venue = this.location?.venue,
        city = this.location?.city,
        countryCode = this.location?.countryCode,
    )
}

fun LocalEventStage.toEventStage(): EventStage {
    val location = if (this.venue != null || this.city != null || this.countryCode != null) {
        Location(
            venue = this.venue.orEmpty(),
            city = this.city.orEmpty(),
            countryCode = this.countryCode.orEmpty(),
        )
    } else {
        null
    }

    return EventStage(
        id = EventStage.Id(this.id),
        name = this.name,
        region = this.region,
        startDateUTC = this.startDateUTC,
        endDateUTC = this.endDateUTC,
        liquipedia = this.liquipedia,
        qualifier = this.qualifier,
        lan = this.lan,
        location = location,
    )
}
