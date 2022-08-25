package com.adammcneilly.pocketleague.shared.screens.eventdetail

import com.adammcneilly.pocketleague.feature.core.ScreenInitSettings
import com.adammcneilly.pocketleague.shared.screens.Navigation

/**
 * Returns the [ScreenInitSettings] for our [com.adammcneilly.pocketleague.shared.screens.AppScreens.EventDetail] screen.
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
