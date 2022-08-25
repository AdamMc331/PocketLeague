package com.adammcneilly.pocketleague.shared.screens.records

import com.adammcneilly.pocketleague.feature.core.ScreenInitSettings

/**
 * Returns the [ScreenInitSettings] for our [com.adammcneilly.pocketleague.shared.screens.AppScreens.Records] screen.
 */
fun initRecords(): ScreenInitSettings {
    return ScreenInitSettings(
        title = "Records",
        initState = {
            RecordsViewState()
        },
        callOnInit = {
            // Coming soon.
        },
        reInitOnEachNavigation = false,
    )
}
