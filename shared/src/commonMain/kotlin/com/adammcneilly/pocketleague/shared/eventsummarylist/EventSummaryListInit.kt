package com.adammcneilly.pocketleague.shared.eventsummarylist

import com.adammcneilly.pocketleague.shared.Navigation
import com.adammcneilly.pocketleague.shared.ScreenInitSettings
import com.adammcneilly.pocketleague.shared.datalayer.objects.EventListRequestBody

/**
 * Initialize the event summary list screen.
 */
fun Navigation.initEventSummaryList() = ScreenInitSettings(
    title = "Event Summaries",
    initState = {
        EventSummaryListState()
    },
    callOnInit = {
        val defaultRequestBody = EventListRequestBody()

        events.getEvents(defaultRequestBody)
    },
)
