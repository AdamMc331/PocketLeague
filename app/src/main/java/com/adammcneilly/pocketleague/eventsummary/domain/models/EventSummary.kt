package com.adammcneilly.pocketleague.eventsummary.domain.models

import java.time.LocalDate

/**
 * Represents an individual RLCS Event. Example params below.
 *
 * @property[id] The unique identifier of the event, like 1234.
 * @property[eventName] The name for the event, which can be "Open Qualifier" or "Main Event".
 * @property[tournamentName] The broader name for the tournament being captured, such as "RLCS 2021-22 Season - Winter Split Regional 1 - North America".
 * @property[startDate] The [LocalDate] that this event starts on.
 */
data class EventSummary(
    val id: String,
    val eventName: String,
    val tournamentName: String,
    val startDate: LocalDate,
)
