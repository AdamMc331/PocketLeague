package com.adammcneilly.pocketleague.shared.core.models

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone

/**
 * A high level overview of an event including its [phases].
 */
data class EventOverview(
    val name: String,
    val startDate: LocalDateTime,
    val timeZone: TimeZone,
    val phases: List<PhaseOverview>,
    val standings: Standings,
)
