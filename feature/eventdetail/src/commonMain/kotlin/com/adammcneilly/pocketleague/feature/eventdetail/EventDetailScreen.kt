package com.adammcneilly.pocketleague.feature.eventdetail

import com.adammcneilly.pocketleague.data.event.EventService
import com.adammcneilly.pocketleague.feature.core.Screen
import com.adammcneilly.pocketleague.feature.core.ScreenInitSettings
import com.adammcneilly.pocketleague.feature.core.ScreenParams

class EventDetailScreen(
    params: EventDetailParams,
    eventService: EventService,
    eventProcessor: (EventDetailScreenEvent) -> Unit,
) : Screen {
    override val asString: String = "event_detail_$params"

    override val navigationLevel: Int = 1

    override val initSettings: (ScreenParams?) -> ScreenInitSettings = {
        initEventDetail(
            params,
            eventService,
            eventProcessor,
        )
    }

    override val stackableInstances: Boolean = false
}
