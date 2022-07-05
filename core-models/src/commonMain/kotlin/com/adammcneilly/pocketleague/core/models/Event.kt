package com.adammcneilly.pocketleague.core.models

import kotlinx.datetime.Instant

/**
 * Defines any Rocket League event that can occur to allow a number of teams or players
 * to compete.
 *
 * @property[id] A unique identifier for this event.
 * @property[name] The description of this event, such as "RLCS Winter Split Regional 1".
 * @property[startDateUTC] The date that this event begins.
 * @property[endDateUTC] The date that this event stops.
 * @property[imageUrl] The URL to the remotely hosted image for this event.
 * @property[stages] The collection of different stages that will make up this event, including qualifiers
 * and main event.
 * @property[tier] An explanation of the notoriety of this event, like S-tier or A-tier.
 * @property[mode] A description of the game mode for this event. A 3 means 3v3, a 2 means 2v2, etc.
 * @property[region] The region this event is taking place in, like NA or EU.
 * @property[lan] True if this is a local in person event, false if it's online.
 * @property[prize] The amount of money awarded to the winners of the event.
 */
data class Event(
    val id: String = "",
    val name: String = "",
    val startDateUTC: Instant? = null,
    val endDateUTC: Instant? = null,
    val imageUrl: String? = null,
    val stages: List<EventStage> = emptyList(),
    val tier: EventTier = EventTier.Unknown,
    val mode: String = "",
    val region: EventRegion = EventRegion.Unknown,
    val lan: Boolean = false,
    val prize: Prize? = null,
)
