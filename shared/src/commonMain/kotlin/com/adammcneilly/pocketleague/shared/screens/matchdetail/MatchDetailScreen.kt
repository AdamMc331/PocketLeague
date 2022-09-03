package com.adammcneilly.pocketleague.shared.screens.matchdetail

import com.adammcneilly.pocketleague.feature.core.Screen
import com.adammcneilly.pocketleague.feature.core.ScreenInitSettings
import com.adammcneilly.pocketleague.feature.core.ScreenParams

object MatchDetailScreen : Screen {
    override val asString: String = "match_detail"

    override val navigationLevel: Int = 2

    override val initSettings: (ScreenParams?) -> ScreenInitSettings = {
        // ARM - Can we use reified?
        initMatchDetail(it as MatchDetailParams)
    }

    override val stackableInstances: Boolean = false
}
