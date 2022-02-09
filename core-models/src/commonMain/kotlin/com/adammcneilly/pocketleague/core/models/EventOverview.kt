package com.adammcneilly.pocketleague.core.models

/**
 * A high level overview of an event including its [phases].
 */
data class EventOverview(
    val name: String,
    val startDateEpochSeconds: Long,
    val phases: List<PhaseOverview>,
    val standings: Standings,
)
