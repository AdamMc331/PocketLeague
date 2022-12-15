package com.adammcneilly.pocketleague.data.local.mappers

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.EventRegion
import com.adammcneilly.pocketleague.core.models.EventStage
import com.adammcneilly.pocketleague.core.models.EventTier
import com.adammcneilly.pocketleague.sqldelight.EventWithStages
import com.adammcneilly.pocketleague.sqldelight.LocalEvent

fun EventWithStages.parseEvent(): Event {
    return Event(
        id = this.localEventId,
        name = this.localEventName,
        startDateUTC = this.localEventStartDateUTC,
        endDateUTC = this.localEventEndDateUTC,
        imageURL = this.localEventImageURL,
        stages = emptyList(),
        tier = EventTier.valueOf(this.localEventTier),
        mode = this.localEventMode,
        region = EventRegion.valueOf(this.localEventRegion),
        lan = this.localEventLan,
        // ARM - NEED TO FIX
        prize = null,
    )
}

fun EventWithStages.parseStage(): EventStage {
    return EventStage(
        id = this.localEventStageId,
        name = this.localEventStageName,
        region = this.localEventStageRegion,
        startDateUTC = this.localEventStageStartDateUTC,
        endDateUTC = this.localEventEndDateUTC,
        liquipedia = this.localEventStageLiquipedia,
        qualifier = this.localEventStageQualifier,
        lan = this.localEventStageLan,
    )
}

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
