package com.adammcneilly.pocketleague.data.game

import com.adammcneilly.pocketleague.core.models.DataState
import com.adammcneilly.pocketleague.core.models.Game

/**
 * Defines the data contract for all requests within the game space.
 */
interface GameService {

    /**
     * Requests a list of [Game] entities for the given [request].
     */
    suspend fun fetchGamesForMatch(request: MatchGamesRequest): DataState<List<Game>>
}
