package com.adammcneilly.pocketleague.core.models

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone

/**
 * Represents an individual RLCS Event. Example params below.
 *
 * @property[id] The unique identifier of the event, like 1234.
 * @property[eventName] The name for the event, which can be "Open Qualifier" or "Main Event".
 * @property[tournamentName] The broader name for the tournament being captured, such as
 * "RLCS 2021-22 Season - Winter Split Regional 1 - North America".
 * @property[tournamentImageUrl] An image URL to some image that will be used to identify this tournament.
 * @property[startDate] The local date time, in the [timeZone], that this event will begin.
 * @property[timeZone] The [TimeZone] that this event is scheduled in, so we can map [startDate] to the proper instant.
 * @property[numEntrants] The number of teams registered or participating in this event. Optional if
 * no one has registered yet.
 * @property[isOnline] True if this is an event taking place digitally, false if in person LAN.
 */
data class EventSummary(
    val id: String,
    val eventName: String,
    val tournamentName: String,
    val tournamentImageUrl: String?,
    val startDate: LocalDateTime,
    val timeZone: TimeZone,
    val numEntrants: Int?,
    val isOnline: Boolean,
)
