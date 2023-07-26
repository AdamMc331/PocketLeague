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
        .groupBy { eventWithStage ->
            val prize = if (eventWithStage.localEventPrizeAmount != null && eventWithStage.localEventPrizeCurrency != null) {
                Prize(
                    amount = eventWithStage.localEventPrizeAmount,
                    currency = eventWithStage.localEventPrizeCurrency,
                )
            } else {
                null
            }

            Event(
                id = Event.Id(eventWithStage.localEventId),
                name = eventWithStage.localEventName,
                startDateUTC = eventWithStage.localEventStartDateUTC,
                endDateUTC = eventWithStage.localEventEndDateUTC,
                imageURL = eventWithStage.localEventImageURL,
                stages = emptyList(),
                tier = EventTier.valueOf(eventWithStage.localEventTier),
                mode = eventWithStage.localEventMode,
                region = EventRegion.valueOf(eventWithStage.localEventRegion),
                lan = eventWithStage.localEventLan,
                prize = prize,
            )
        }
        .map { (event, stageRows) ->
            val stages = stageRows.map { eventWithStage ->
                val location =
                    if (eventWithStage.localEventStageVenue != null && eventWithStage.localEventStageCity != null && eventWithStage.localEventStageCountryCode != null) {
                        Location(
                            eventWithStage.localEventStageVenue,
                            eventWithStage.localEventStageCity,
                            eventWithStage.localEventStageCountryCode,
                        )
                    } else {
                        null
                    }

                EventStage(
                    id = EventStage.Id(eventWithStage.localEventStageId),
                    name = eventWithStage.localEventStageName,
                    region = eventWithStage.localEventStageRegion,
                    startDateUTC = eventWithStage.localEventStageStartDateUTC,
                    endDateUTC = eventWithStage.localEventStageEndDateUTC,
                    liquipedia = eventWithStage.localEventStageLiquipedia,
                    qualifier = eventWithStage.localEventStageQualifier,
                    lan = eventWithStage.localEventStageLan,
                    location = location,
                )
            }

            event.copy(
                stages = stages,
            )
        }
}
// fun EventWithStage.toEventStagePair(): Pair<Event, EventStage> {
//    val event = Event(
//        id = Event.Id(this.localEventId),
//        name = this.localEventName,
//        startDateUTC = this.localEventStartDateUTC,
//        endDateUTC = this.localEventEndDateUTC,
//        imageURL = null
//    )
// }
