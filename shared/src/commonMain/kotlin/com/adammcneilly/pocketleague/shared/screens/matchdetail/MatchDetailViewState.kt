package com.adammcneilly.pocketleague.shared.screens.matchdetail

import com.adammcneilly.pocketleague.core.displaymodels.GameDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.feature.ScreenState
import com.adammcneilly.pocketleague.core.models.Match

/**
 * The UI state for detailed information about a [Match].
 */
data class MatchDetailViewState(
    val matchId: String = "",
    val matchDetail: MatchDetailDisplayModel? = null,
    val games: List<GameDetailDisplayModel> = emptyList(),
) : ScreenState {

    override val title: String?
        get() {
            val matchDetail = this.matchDetail?.takeIf {
                !it.isPlaceholder
            } ?: return null

            val blueTeamName = matchDetail.blueTeamResult.team.name
            val orangeTeamName = matchDetail.orangeTeamResult.team.name

            return "$blueTeamName vs $orangeTeamName"
        }

    companion object {
        private const val NUM_PLACEHOLDER_GAMES = 7
    }
}
