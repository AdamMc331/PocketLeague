package com.adammcneilly.pocketleague.data.event

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.Team

/**
 * An implementation of [RemoteEventService] that combines data from an
 * [octaneGGEventService] and a [startGGEventService].
 *
 * @see [getEventById] for details on why we combined these data sources.
 */
class CombinedStartOctaneEventService(
    private val octaneGGEventService: RemoteEventService,
    private val startGGEventService: RemoteEventService,
) : RemoteEventService {

    override suspend fun getUpcomingEvents(): Result<List<Event>> {
        return startGGEventService.getUpcomingEvents()
    }

    /**
     * Requests an event by [eventId] from the [startGGEventService], and then looks up that same
     * event on [octaneGGEventService], so that we can fetch all possible information about an event
     * that we may want to display.
     */
    override suspend fun getEventById(eventId: String): Result<Event> {
        val startEventResult = startGGEventService.getEventById(eventId)

        return startEventResult.map { startEvent ->
            // Look up this event on octane
            // and map in fields that were not available from Start.GG.
            val octaneName = startEvent.parseEventNameForOctaneGG()

            val octaneEvent = octaneGGEventService.getEventByName(octaneName).getOrThrow()

            startEvent.copy(
                tier = octaneEvent.tier,
                mode = octaneEvent.mode,
                region = octaneEvent.region,
                prize = octaneEvent.prize,
            )
        }
    }

    override suspend fun getEventParticipants(eventId: String): Result<List<Team>> {
        return startGGEventService.getEventParticipants(eventId)
    }

    override suspend fun getOngoingEvents(): Result<List<Event>> {
        return startGGEventService.getOngoingEvents()
    }

    override suspend fun getEventByName(eventName: String): Result<Event> {
        throw UnsupportedOperationException("Looking up an event by name is not supported by this service.")
    }
}

/**
 * Assuming this is operated on an event requested via a [StartGGEventService], this converts an
 * event name from the start.gg API which follows the format:
 *
 * {season} - {split} {event} - {region}
 * RLCS 2022-23 - Spring Invitational - Europe
 *
 * And convert it to a name that matches the octane.gg structure, which is:
 *
 * {season} {split} {region} {event}
 * RLCS 2022-23 Spring Europe Regional 3
 *
 * Note that we also need to map the event name into a different name based on the event regional number.
 */
private fun Event.parseEventNameForOctaneGG(): String {
    val startName = this.name.orEmpty()
    val startNameParts = startName.split(" - ")

    return if (startNameParts.size != 3) {
        ""
    } else {
        val splitEvent = startNameParts[1].split(" ")

        if (splitEvent.size != 2) {
            ""
        } else {
            val season = startNameParts[0]
            val (split, startEvent) = splitEvent
            val region = startNameParts[2]

            val octaneEvent = when (startEvent) {
                "Cup" -> "Regional 1"
                "Open" -> "Regional 2"
                "Invitational" -> "Regional 3"
                else -> startEvent
            }

            "$season $split $region $octaneEvent"
        }
    }
}
