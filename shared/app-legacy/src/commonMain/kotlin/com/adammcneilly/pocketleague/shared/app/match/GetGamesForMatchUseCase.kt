package com.adammcneilly.pocketleague.shared.app.match

import com.adammcneilly.pocketleague.core.displaymodels.GameDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.toDetailDisplayModel
import com.adammcneilly.pocketleague.core.models.Game
import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.data.game.GameService
import com.adammcneilly.pocketleague.data.game.MatchGamesRequest

/**
 * Request a collection of games for a specific match ID.
 */
class GetGamesForMatchUseCase(
    private val gameService: GameService,
) {
    /**
     * @see [GetGamesForMatchUseCase]
     */
    suspend fun invoke(
        matchId: String,
    ): List<GameDetailDisplayModel> {
        val request = MatchGamesRequest(Match.Id(matchId))
        return gameService.fetchGamesForMatch(request)
            .getOrNull()
            .orEmpty()
            .map(Game::toDetailDisplayModel)
    }
}
