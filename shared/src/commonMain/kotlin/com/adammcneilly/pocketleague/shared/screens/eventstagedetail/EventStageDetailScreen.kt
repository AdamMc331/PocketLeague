package com.adammcneilly.pocketleague.shared.screens.eventstagedetail

import com.adammcneilly.pocketleague.feature.core.Screen
import com.adammcneilly.pocketleague.feature.core.ScreenInitSettings
import com.adammcneilly.pocketleague.feature.core.ScreenParams

object EventStageDetailScreen : Screen {
    override val asString: String = "event_stage_detail"

    override val navigationLevel: Int = 3

    override val initSettings: (ScreenParams) -> ScreenInitSettings = {
        // ARM - Can we use reified here?
        initEventStageDetail(it as EventStageDetailParams)
    }

    override val stackableInstances: Boolean = false
}
