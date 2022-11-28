package com.adammcneilly.pocketleague.data.local.mappers

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.EventRegion
import com.adammcneilly.pocketleague.core.models.EventTier
import com.adammcneilly.pocketleague.sqldelight.LocalEvent

/**
 * Convert an [Event] from our domain to a SqlDelight [LocalEvent].
 */
fun Event.toLocalEvent(): LocalEvent {
    return LocalEvent(
        id = this.id,
        name = this.name,
        startDateUTC = this.startDateUTC,
        endDateUTC = this.endDateUTC,
        imageURL = this.imageURL,
        tier = this.tier.name,
        mode = this.mode,
        region = this.region.name,
        lan = this.lan,
    )
}

/**
 * Convert a SqlDelight [LocalEvent] to an [Event] from our domain.
 */
fun LocalEvent.toEvent(): Event {
    return Event(
        id = this.id,
        name = this.name,
        startDateUTC = this.startDateUTC,
        endDateUTC = this.endDateUTC,
        imageURL = this.imageURL,
        // ARM - NEED TO FIX
        stages = emptyList(),
        tier = EventTier.valueOf(this.tier),
        mode = this.mode,
        region = EventRegion.valueOf(this.region),
        lan = this.lan,
        // ARM - NEED TO FIX
        prize = null,
    )
}
