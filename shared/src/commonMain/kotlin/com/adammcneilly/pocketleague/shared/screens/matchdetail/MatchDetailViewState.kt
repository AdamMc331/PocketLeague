package com.adammcneilly.pocketleague.shared.screens.matchdetail

import com.adammcneilly.pocketleague.core.models.Game
import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.shared.data.DataState
import com.adammcneilly.pocketleague.shared.screens.ScreenState

/**
 * The UI state for detailed information about a [Match].
 */
data class MatchDetailViewState(
    val showLoading: Boolean = true,
    val match: Match = Match(),
    val gamesDataState: DataState<List<Game>> = DataState.Loading,
    val errorMessage: String? = null,
) : ScreenState {

    val games: List<Game>
        get() = when (gamesDataState) {
            is DataState.Loading -> {
                listOf(Game(), Game(), Game())
            }
            is DataState.Success -> {
                gamesDataState.data
            }
            is DataState.Error -> {
                emptyList()
            }
        }
}
