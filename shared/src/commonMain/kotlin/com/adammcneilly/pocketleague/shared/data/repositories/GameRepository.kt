package com.adammcneilly.pocketleague.shared.data.repositories

import com.adammcneilly.pocketleague.core.models.Game
import com.adammcneilly.pocketleague.shared.data.DataState
import com.adammcneilly.pocketleague.shared.data.models.MatchGamesRequest
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
