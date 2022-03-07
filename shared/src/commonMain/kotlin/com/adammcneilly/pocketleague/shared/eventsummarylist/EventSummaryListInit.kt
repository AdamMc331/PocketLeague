package com.adammcneilly.pocketleague.shared.eventsummarylist

import com.adammcneilly.pocketleague.shared.Navigation
import com.adammcneilly.pocketleague.shared.ScreenInitSettings

fun Navigation.initEventSummaryList() = ScreenInitSettings(
    title = "Event Summaries",
    initState = {
        EventSummaryListState()
    },
    callOnInit = {
        // Coming soon
    },
)
