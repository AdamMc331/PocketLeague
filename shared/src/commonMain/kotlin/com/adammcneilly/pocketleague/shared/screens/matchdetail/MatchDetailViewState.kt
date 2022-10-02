package com.adammcneilly.pocketleague.shared.screens.matchdetail

import com.adammcneilly.pocketleague.core.data.DataState
import com.adammcneilly.pocketleague.core.displaymodels.GameDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.shared.screens.ScreenState

/**
 * The UI state for detailed information about a [Match].
 */
data class MatchDetailViewState(
    val matchId: String = "",
    private val matchDetailState: DataState<MatchDetailDisplayModel> = DataState.Loading,
    private val gamesState: DataState<List<GameDetailDisplayModel>> = DataState.Loading,
) : ScreenState {

    /**
     * This returns a [MatchDetailDisplayModel] if available based on the current
     * [matchDetailState].
     */
    val matchDetail: MatchDetailDisplayModel?
        get() = when (matchDetailState) {
            is DataState.Error -> {
                null
            }
            DataState.Loading -> {
                MatchDetailDisplayModel.placeholder
            }
            is DataState.Success -> {
                matchDetailState.data
            }
        }

    val games: List<GameDetailDisplayModel>?
        get() = when (gamesState) {
            is DataState.Error -> {
                null
            }
            DataState.Loading -> {
                (1..NUM_PLACEHOLDER_GAMES).map {
                    GameDetailDisplayModel.placeholder
                }
            }
            is DataState.Success -> {
                gamesState.data
            }
        }

    companion object {
        private const val NUM_PLACEHOLDER_GAMES = 7
    }
}
