package com.adammcneilly.pocketleague.shared.screens.eventstagedetail

import com.adammcneilly.pocketleague.feature.core.ScreenInitSettings

/**
 * Creates the [ScreenInitSettings] for the event stage detail screen.
 */
fun initEventStageDetail(
    params: EventStageDetailParams,
): ScreenInitSettings {
    return ScreenInitSettings(
        title = "Event Stage Detail",
        initState = {
            EventStageDetailViewState(
                eventId = params.eventId,
                stageId = params.stageId,
            )
        },
        callOnInit = {
            // ARM - COMING SOON
//            events.loadEventStageDetail(
//                eventId = params.eventId,
//                stageId = params.stageId,
//            )
        },
        reInitOnEachNavigation = false,
    )
}
