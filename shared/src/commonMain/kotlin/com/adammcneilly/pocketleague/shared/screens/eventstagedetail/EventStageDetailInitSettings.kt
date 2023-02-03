package com.adammcneilly.pocketleague.shared.screens.eventstagedetail

import com.adammcneilly.pocketleague.shared.screens.Navigation
import com.adammcneilly.pocketleague.shared.screens.ScreenInitSettings

/**
 * Creates the [ScreenInitSettings] for the event stage detail screen.
 */
fun Navigation.initEventStageDetail(
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
            events.loadEventStageDetail(
                eventId = params.eventId,
                stageId = params.stageId,
            )
        },
        reInitOnEachNavigation = false,
    )
}
