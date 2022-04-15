package com.adammcneilly.pocketleague.shared.models

/**
 * Defines any Rocket League event that can occur to allow a number of teams or players
 * to compete.
 *
 * @property[id] A unique identifier for this event.
 * @property[name] The description of this event, such as "RLCS Winter Split Regional 1".
 */
data class Event(
    val id: String,
    val name: String,
)
