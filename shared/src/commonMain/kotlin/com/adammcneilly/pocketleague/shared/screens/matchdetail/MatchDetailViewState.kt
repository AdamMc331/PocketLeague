package com.adammcneilly.pocketleague.shared.screens.matchdetail

import com.adammcneilly.pocketleague.shared.models.Game
import com.adammcneilly.pocketleague.shared.models.Match
import com.adammcneilly.pocketleague.shared.screens.ScreenState

/**
 * The UI state for detailed information about a [Match].
 */
data class MatchDetailViewState(
    val showLoading: Boolean = true,
    val match: Match = Match(),
    val games: List<Game> = emptyList(),
    val errorMessage: String? = null,
) : ScreenState
