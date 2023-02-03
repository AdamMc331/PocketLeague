package com.adammcneilly.pocketleague.shared.screens.records

import com.adammcneilly.pocketleague.shared.screens.Navigation
import com.adammcneilly.pocketleague.shared.screens.ScreenInitSettings

/**
 * Returns the [ScreenInitSettings] for our [com.adammcneilly.pocketleague.shared.screens.Screens.Records] screen.
 */
fun Navigation.initRecords(): ScreenInitSettings {
    return ScreenInitSettings(
        title = "Records",
        initState = {
            RecordsViewState()
        },
        callOnInit = {
            // Coming soon.
        },
        reInitOnEachNavigation = false
    )
}
