package com.adammcneilly.pocketleague.shared.screens.eventstagedetail

import com.adammcneilly.pocketleague.shared.screens.Navigation
import com.adammcneilly.pocketleague.shared.screens.ScreenInitSettings

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
            // Coming soon.
        },
        reInitOnEachNavigation = false,
    )
}
