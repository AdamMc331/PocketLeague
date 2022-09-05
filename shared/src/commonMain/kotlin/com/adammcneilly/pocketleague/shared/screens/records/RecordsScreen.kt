package com.adammcneilly.pocketleague.shared.screens.records

import com.adammcneilly.pocketleague.feature.core.Screen
import com.adammcneilly.pocketleague.feature.core.ScreenInitSettings
import com.adammcneilly.pocketleague.feature.core.ScreenParams

object RecordsScreen : Screen {
    override val asString: String = "records"

    override val navigationLevel: Int = 1

    override val initSettings: (ScreenParams?) -> ScreenInitSettings = {
        initRecords()
    }

    override val stackableInstances: Boolean = true
}
