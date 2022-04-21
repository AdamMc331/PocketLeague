package com.adammcneilly.pocketleague.shared.matchdetail

import com.adammcneilly.pocketleague.shared.data.DataState
import com.adammcneilly.pocketleague.shared.models.Game
import kotlinx.coroutines.flow.Flow

/**
 * Consumes a match ID and requests a list of [Game] entities for that match.
 */
interface GetMatchGamesUseCase {

    /**
     * @see [GetMatchGamesUseCase]
     */
    operator fun invoke(matchId: String): Flow<DataState<List<Game>>>
}
