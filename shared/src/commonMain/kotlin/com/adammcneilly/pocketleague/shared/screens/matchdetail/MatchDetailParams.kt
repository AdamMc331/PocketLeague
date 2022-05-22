package com.adammcneilly.pocketleague.shared.screens.matchdetail

import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.shared.screens.ScreenParams

/**
 * Parameters that will be passed into the match detail screen.
 *
 * @property[match] The [Match] entity to request detailed information for.
 */
data class MatchDetailParams(
    val match: Match,
) : ScreenParams
