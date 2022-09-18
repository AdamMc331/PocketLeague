package com.adammcneilly.pocketleague.data.game

import com.adammcneilly.pocketleague.core.data.DataState
import com.adammcneilly.pocketleague.core.models.Game

/**
 * Defines the data contract for all requests within the game space.
 */
interface GameService {

    /**
     * Requests a list of [Game] entities for the given [request].
     */
    suspend fun fetchGamesForMatch(request: MatchGamesRequest): DataState<List<Game>>

    companion object {
        /**
         * Provide a default implementation of a [GameService] so that consumers of this module
         * have no idea what implementations are available.
         */
        fun provideDefault(): GameService {
            return OctaneGGGameService()
        }
    }
}
