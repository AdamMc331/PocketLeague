package com.adammcneilly.pocketleague.feature.eventdetail

import com.adammcneilly.pocketleague.data.event.EventService
import com.adammcneilly.pocketleague.feature.core.ScreenInitSettings

fun initEventDetail(
    params: EventDetailParams,
    eventService: EventService,
    eventProcessor: (EventDetailScreenEvent) -> Unit,
): ScreenInitSettings {
    return ScreenInitSettings(
        title = "Event Detail",
        initState = {
            EventDetailViewState()
        },
        callOnInit = {
            loadEventDetail(
                params,
                eventService,
                eventProcessor,
            )
        },
        reInitOnEachNavigation = true,
    )
}
