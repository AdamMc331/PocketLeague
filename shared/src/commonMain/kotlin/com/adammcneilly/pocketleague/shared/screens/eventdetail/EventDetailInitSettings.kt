package com.adammcneilly.pocketleague.shared.screens.eventdetail

import com.adammcneilly.pocketleague.shared.screens.Navigation
import com.adammcneilly.pocketleague.shared.screens.ScreenInitSettings

/**
 * Returns the [ScreenInitSettings] for our [com.adammcneilly.pocketleague.shared.screens.Screens.EventDetail] screen.
 */
fun Navigation.initEventDetail(
    params: EventDetailParams,
): ScreenInitSettings {
    return ScreenInitSettings(
        title = "Event Detail",
        initState = {
            EventDetailViewState(
                eventId = params.eventId,
            )
        },
        callOnInit = {
            events.loadEventDetail(
                eventId = params.eventId,
            )
        },
        reInitOnEachNavigation = false,
    )
}
