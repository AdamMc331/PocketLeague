package com.adammcneilly.pocketleague.shared.eventoverview

import com.adammcneilly.pocketleague.shared.Navigation
import com.adammcneilly.pocketleague.shared.ScreenInitSettings
import com.adammcneilly.pocketleague.shared.ScreenParams
import kotlinx.serialization.Serializable

@Serializable
data class EventOverviewParams(val eventId: String) : ScreenParams

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
