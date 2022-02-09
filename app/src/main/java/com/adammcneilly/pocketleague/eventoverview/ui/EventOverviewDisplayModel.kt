package com.adammcneilly.pocketleague.eventoverview.ui

import com.adammcneilly.pocketleague.models.EventOverview
import com.adammcneilly.pocketleague.phase.ui.PhaseDisplayModel
import com.adammcneilly.pocketleague.standings.ui.StandingsDisplayModel

/**
 * A user friendly representation of an [EventOverview] to be shown on the UI.
 */
data class EventOverviewDisplayModel(
    val eventName: String,
    val startDate: String,
    val phases: List<PhaseDisplayModel>,
    val standings: StandingsDisplayModel,
)
