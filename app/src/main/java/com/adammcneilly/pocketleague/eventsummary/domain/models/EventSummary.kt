package com.adammcneilly.pocketleague.eventsummary.domain.models

import java.time.ZonedDateTime

/**
 * Represents an individual RLCS Event. Example params below.
 *
 * @property[id] The unique identifier of the event, like 1234.
 * @property[eventName] The name for the event, which can be "Open Qualifier" or "Main Event".
 * @property[tournamentName] The broader name for the tournament being captured, such as
 * "RLCS 2021-22 Season - Winter Split Regional 1 - North America".
 * @property[startDate] The [ZonedDateTime] that this event starts on.
 * @property[numEntrants] The number of teams registered or participating in this event. Optional if
 * no one has registered yet.
 * @property[isOnline] True if this is an event taking place digitally, false if in person LAN.
 */
data class EventSummary(
    val id: String,
    val eventName: String,
    val tournamentName: String,
    val tournamentImageUrl: String,
    val startDate: ZonedDateTime,
    val numEntrants: Int?,
    val isOnline: Boolean,
)
