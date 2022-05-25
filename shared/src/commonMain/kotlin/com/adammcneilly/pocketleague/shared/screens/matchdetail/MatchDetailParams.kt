package com.adammcneilly.pocketleague.shared.screens.matchdetail

import com.adammcneilly.pocketleague.shared.screens.ScreenParams

/**
 * Parameters that will be passed into the match detail screen.
 *
 * @property[matchId] The The identifier of the match we want to show detailed information for.
 */
data class MatchDetailParams(
    val matchId: String = "",
) : ScreenParams
