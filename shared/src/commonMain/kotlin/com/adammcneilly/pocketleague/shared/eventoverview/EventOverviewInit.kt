package com.adammcneilly.pocketleague.shared.eventoverview

import com.adammcneilly.pocketleague.shared.Navigation
import com.adammcneilly.pocketleague.shared.ScreenInitSettings

/**
 * Initializes our event overview screen.
 */
fun Navigation.initEventOverview(
    params: EventOverviewParams,
) = ScreenInitSettings(
    title = "Event Overview",
    initState = {
        EventOverviewState(eventId = params.eventId)
    },
    callOnInit = {
        // Load for params
    },
    reInitOnEachNavigation = true,
)
