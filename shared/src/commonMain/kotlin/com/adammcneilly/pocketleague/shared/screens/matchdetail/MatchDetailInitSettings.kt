package com.adammcneilly.pocketleague.shared.screens.matchdetail

import com.adammcneilly.pocketleague.shared.screens.Navigation
import com.adammcneilly.pocketleague.shared.screens.ScreenInitSettings

/**
 * Returns the [ScreenInitSettings] for our [com.adammcneilly.pocketleague.shared.screens.Screens.MatchDetail] screen.
 */
fun Navigation.initMatchDetail(
    params: MatchDetailParams,
): ScreenInitSettings {
    return ScreenInitSettings(
        title = "Match Detail",
        initState = {
            MatchDetailViewState(
                match = params.match,
            )
        },
        callOnInit = {
            events.getGamesForMatch(params.match.id)
        },
        reInitOnEachNavigation = false,
    )
}
