package com.adammcneilly.pocketleague.shared.app.match

import com.adammcneilly.pocketleague.core.displaymodels.GameDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel

data class MatchDetailUiState(
    val match: MatchDetailDisplayModel,
    val games: List<GameDetailDisplayModel>,
    val selectedGame: GameDetailDisplayModel?,
) {
    companion object {
        /**
         * Returns a default instance of [MatchDetailUiState] that represents
         * a loading state.
         */
        fun placeholderState(): MatchDetailUiState {
            return MatchDetailUiState(
                match = MatchDetailDisplayModel.placeholder,
                games = listOf(
                    GameDetailDisplayModel.placeholder,
                    GameDetailDisplayModel.placeholder,
                    GameDetailDisplayModel.placeholder,
                ),
                selectedGame = null,
            )
        }
    }
}
