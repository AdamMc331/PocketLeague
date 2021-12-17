package com.adammcneilly.pocketleague.eventoverview.domain.models

import com.adammcneilly.pocketleague.phase.domain.models.Phase

/**
 * A high level overview of an event including its [phases].
 */
data class EventOverview(
    val name: String,
    val phases: List<Phase>,
)
