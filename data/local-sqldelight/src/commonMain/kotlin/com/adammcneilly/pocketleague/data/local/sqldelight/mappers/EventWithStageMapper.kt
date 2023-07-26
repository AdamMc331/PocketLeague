package com.adammcneilly.pocketleague.data.local.sqldelight.mappers

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.EventRegion
import com.adammcneilly.pocketleague.core.models.EventStage
import com.adammcneilly.pocketleague.core.models.EventTier
import com.adammcneilly.pocketleague.core.models.Location
import com.adammcneilly.pocketleague.core.models.Prize
import com.adammcneilly.pocketleague.sqldelight.EventWithStage

/**
 * Converts an [EventWithStage]  SQLDelight entity list to a list of [Event] objects.
 */
fun List<EventWithStage>.toEvents(): List<Event> {
    return this
        .groupBy(EventWithStage::parseEvent)
        .map { (event, stageRows) ->
            event.copy(
                stages = stageRows.map(EventWithStage::parseStage),
            )
        }
}

private fun EventWithStage.parseStage(): EventStage {
    return EventStage(
        id = EventStage.Id(localEventStageId),
        name = localEventStageName,
        region = localEventStageRegion,
        startDateUTC = localEventStageStartDateUTC,
        endDateUTC = localEventStageEndDateUTC,
        liquipedia = localEventStageLiquipedia,
        qualifier = localEventStageQualifier,
        lan = localEventStageLan,
        location = parseStageLocation(),
    )
}

private fun EventWithStage.parseStageLocation(): Location? {
    return Location(
        venue = localEventStageVenue.orEmpty(),
        city = localEventStageCity.orEmpty(),
        countryCode = localEventStageCountryCode.orEmpty(),
    ).takeIf {
        localEventStageVenue != null &&
            localEventStageCity != null &&
            localEventStageCountryCode != null
    }
}

private fun EventWithStage.parseEventPrize(): Prize? {
    return Prize(
        amount = localEventPrizeAmount ?: 0.0,
        currency = localEventPrizeCurrency.orEmpty(),
    ).takeIf {
        localEventPrizeAmount != null &&
            localEventPrizeCurrency != null
    }
}

private fun EventWithStage.parseEvent(): Event {
    return Event(
        id = Event.Id(localEventId),
        name = localEventName,
        startDateUTC = localEventStartDateUTC,
        endDateUTC = localEventEndDateUTC,
        imageURL = localEventImageURL,
        stages = emptyList(),
        tier = EventTier.valueOf(localEventTier),
        mode = localEventMode,
        region = EventRegion.valueOf(localEventRegion),
        lan = localEventLan,
        prize = parseEventPrize(),
    )
}
