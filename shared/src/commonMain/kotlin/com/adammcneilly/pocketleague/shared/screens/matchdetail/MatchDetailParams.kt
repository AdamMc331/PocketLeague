package com.adammcneilly.pocketleague.shared.screens.matchdetail

import com.adammcneilly.pocketleague.core.feature.ScreenParams
import com.adammcneilly.pocketleague.core.models.Match

/**
 * Parameters that will be passed into the match detail screen.
 *
 * @property[matchId] The identifier of the match to request details for.
 */
data class MatchDetailParams(
    val matchId: Match.Id,
) : ScreenParams
