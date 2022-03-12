package com.adammcneilly.pocketleague.shared.eventoverview

import com.adammcneilly.pocketleague.core.models.EventOverview

/**
 * A user friendly representation of an [EventOverview] to be shown on the UI.
 */
data class EventOverviewDisplayModel(
    val eventName: String,
    val startDate: String,
    val phases: List<PhaseDisplayModel>,
    val standings: StandingsDisplayModel,
)
