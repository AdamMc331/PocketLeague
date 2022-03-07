package com.adammcneilly.pocketleague.shared.eventoverview

import com.adammcneilly.pocketleague.shared.Navigation
import com.adammcneilly.pocketleague.shared.ScreenInitSettings

fun Navigation.initEventOverview() = ScreenInitSettings(
    title = "Event Overview",
    initState = {
        EventOverviewState()
    },
    callOnInit = {
        // Coming soon
    },
)
