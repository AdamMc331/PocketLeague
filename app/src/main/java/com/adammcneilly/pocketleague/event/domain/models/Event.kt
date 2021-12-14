package com.adammcneilly.pocketleague.event.domain.models

/**
 * Represents an individual RLCS Event. Example params below.
 *
 * @property[id] The unique identifier of the event, like 1234.
 * @property[eventName] The name for the event, which can be "Open Qualifier" or "Main Event".
 * @property[tournamentName] The broader name for the tournament being captured, such as "RLCS 2021-22 Season - Winter Split Regional 1 - North America".
 */
data class Event(
    val id: String,
    val eventName: String,
    val tournamentName: String,
)
