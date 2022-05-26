package com.adammcneilly.pocketleague.shared.screens.matchdetail

import com.adammcneilly.pocketleague.core.displaymodels.GameDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.shared.screens.ScreenState

/**
 * The UI state for detailed information about a [Match].
 */
data class MatchDetailViewState(
    val showDetailLoading: Boolean = true,
    val matchId: String = "",
    val matchDetail: MatchDetailDisplayModel? = null,
    val detailInfoErrorMessage: String? = null,
    val games: List<GameDetailDisplayModel>? = null,
    val showGamesLoading: Boolean = true,
    val gamesErrorMessage: String? = null,
) : ScreenState
