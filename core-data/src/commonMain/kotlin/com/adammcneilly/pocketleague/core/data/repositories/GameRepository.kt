package com.adammcneilly.pocketleague.core.data.repositories

import com.adammcneilly.pocketleague.core.data.DataState
import com.adammcneilly.pocketleague.core.data.models.MatchGamesRequest
import com.adammcneilly.pocketleague.core.models.Game
import kotlinx.coroutines.flow.Flow

/**
 * Defines the data contract for all requests within the game space.
 */
interface GameRepository {

    /**
     * Requests a list of [Game] entities for the given [request].
     */
    fun fetchGamesForMatch(request: MatchGamesRequest): Flow<DataState<List<Game>>>
}
