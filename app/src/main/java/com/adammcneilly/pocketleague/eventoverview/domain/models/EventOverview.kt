package com.adammcneilly.pocketleague.eventoverview.domain.models

import com.adammcneilly.pocketleague.phase.domain.models.PhaseOverview
import com.adammcneilly.pocketleague.standings.domain.models.Standings
import java.time.ZonedDateTime

/**
 * A high level overview of an event including its [phases].
 */
data class EventOverview(
    val name: String,
    val startDate: ZonedDateTime,
    val phases: List<PhaseOverview>,
    val standings: Standings,
)
