package com.adammcneilly.pocketleague.shared.models

import kotlinx.datetime.LocalDateTime

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
    val startDate: LocalDateTime?,
    val endDate: LocalDateTime?,
)
