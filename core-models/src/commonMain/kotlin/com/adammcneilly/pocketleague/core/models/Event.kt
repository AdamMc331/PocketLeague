package com.adammcneilly.pocketleague.core.models

import kotlinx.datetime.LocalDateTime

/**
 * Defines any Rocket League event that can occur to allow a number of teams or players
 * to compete.
 *
 * @property[id] A unique identifier for this event.
 * @property[name] The description of this event, such as "RLCS Winter Split Regional 1".
 * @property[startDate] The date that this event begins.
 * @property[endDate] The date that this event stops.
 * @property[imageUrl] The URL to the remotely hosted image for this event.
 * @property[stages] The collection of different stages that will make up this event, including qualifiers
 * and main event.
 */
data class Event(
    val id: String = "",
    val name: String = "",
    val startDate: LocalDateTime? = null,
    val endDate: LocalDateTime? = null,
    val imageUrl: String? = null,
    val stages: List<EventStage>? = null,
)
