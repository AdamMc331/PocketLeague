package com.adammcneilly.pocketleague.shared.screens.stats

import com.adammcneilly.pocketleague.feature.core.Screen
import com.adammcneilly.pocketleague.feature.core.ScreenInitSettings
import com.adammcneilly.pocketleague.feature.core.ScreenParams

object StatsScreen : Screen {
    override val asString: String = "stats"

    override val navigationLevel: Int = 1

    override val initSettings: (ScreenParams) -> ScreenInitSettings = {
        initStats()
    }

    override val stackableInstances: Boolean = true
}
