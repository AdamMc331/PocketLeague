package com.adammcneilly.pocketleague.shared.screens.feed

import com.adammcneilly.pocketleague.feature.core.Screen
import com.adammcneilly.pocketleague.feature.core.ScreenInitSettings
import com.adammcneilly.pocketleague.feature.core.ScreenParams

object FeedScreen : Screen {
    override val asString: String = "feed"

    override val navigationLevel: Int = 1

    override val initSettings: (ScreenParams) -> ScreenInitSettings = {
        initFeed()
    }

    override val stackableInstances: Boolean = true
}
