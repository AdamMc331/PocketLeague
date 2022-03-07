package com.adammcneilly.pocketleague.shared

import com.adammcneilly.pocketleague.shared.eventoverview.initEventOverview
import com.adammcneilly.pocketleague.shared.eventsummarylist.initEventSummaryList

/**
 * An enumeration of all screens that appear inside the application.
 *
 * @property[asString] A string identifier for the application, should be unique.
 * @property[navigationLevel] How deep into a navigation stack this screen appears. Defaults to 1.
 * @property[initSettings] A lambda triggered to initialize the screen.
 * @property[stackableInstances] True if more than once instance of this screen can exist on a backstack.
 */
enum class Screens(
    val asString: String,
    val navigationLevel: Int = 1,
    val initSettings: Navigation.(ScreenIdentifier) -> ScreenInitSettings,
    val stackableInstances: Boolean = false,
) {
    EventSummaryList(
        asString = "eventsummarieslist",
        navigationLevel = 1,
        initSettings = { initEventSummaryList() },
    ),

    EventOverview(
        asString = "eventoverview",
        navigationLevel = 2,
        initSettings = { initEventOverview() },
    ),
}
