package com.adammcneilly.pocketleague.shared.screens.matchdetail

import com.adammcneilly.pocketleague.feature.core.ScreenInitSettings

/**
 * Returns the [ScreenInitSettings] for our [com.adammcneilly.pocketleague.shared.screens.AppScreens.MatchDetail] screen.
 */
fun initMatchDetail(
    params: MatchDetailParams,
): ScreenInitSettings {
    return ScreenInitSettings(
        title = "Match Detail",
        initState = {
            MatchDetailViewState(
                matchId = params.matchId,
            )
        },
        callOnInit = {
            // ARM - COMING SOON
//            events.loadMatchDetail(params.matchId)
        },
        reInitOnEachNavigation = false,
    )
}
