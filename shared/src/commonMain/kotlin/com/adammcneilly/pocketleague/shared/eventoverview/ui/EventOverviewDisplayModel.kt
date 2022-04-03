package com.adammcneilly.pocketleague.shared.eventoverview.ui

import com.adammcneilly.pocketleague.shared.core.models.EventOverview
import com.adammcneilly.pocketleague.shared.standings.StandingsDisplayModel

/**
 * A user friendly representation of an [EventOverview] to be shown on the UI.
 */
data class EventOverviewDisplayModel(
    val eventName: String,
    val startDate: String,
    val phases: List<EventOverviewPhaseDisplayModel>,
    val standings: StandingsDisplayModel,
) {

    companion object {
        const val EVENT_DATE_FORMAT = "MMM dd, yyyy"
    }
}
