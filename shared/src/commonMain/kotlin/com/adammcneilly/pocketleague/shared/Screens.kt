package com.adammcneilly.pocketleague.shared

import com.adammcneilly.pocketleague.shared.eventoverview.initEventOverview
import com.adammcneilly.pocketleague.shared.eventsummarylist.initEventSummaryList

// DEFINITION OF ALL SCREENS IN THE APP

enum class Screens(
    val asString: String,
    val navigationLevel: Int = 1,
    val initSettings: Navigation.(ScreenIdentifier) -> ScreenInitSettings,
    val stackableInstances: Boolean = false,
) {
    EventSummaryList("eventsummarieslist", 1, { initEventSummaryList() }, true),
    EventOverview("eventoverview", 2, { initEventOverview() }),
}
