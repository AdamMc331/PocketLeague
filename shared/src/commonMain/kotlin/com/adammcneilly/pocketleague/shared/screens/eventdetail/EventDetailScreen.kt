package com.adammcneilly.pocketleague.shared.screens.eventdetail

import com.adammcneilly.pocketleague.feature.core.Screen
import com.adammcneilly.pocketleague.feature.core.ScreenInitSettings
import com.adammcneilly.pocketleague.feature.core.ScreenParams

object EventDetailScreen : Screen {
    override val asString: String = "event_detail"

    override val navigationLevel: Int = 2

    override val initSettings: (ScreenParams) -> ScreenInitSettings = {
        ScreenInitSettings(
            title = "Event Detail",
            initState = {
                EventDetailViewState()
            },
            callOnInit = {
                // ARM - COMING SOON,
            }
        )
    }

    override val stackableInstances: Boolean = false
}
