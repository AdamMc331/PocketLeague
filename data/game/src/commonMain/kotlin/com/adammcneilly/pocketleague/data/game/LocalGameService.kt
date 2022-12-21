package com.adammcneilly.pocketleague.data.game

import com.adammcneilly.pocketleague.core.models.Game
import kotlinx.coroutines.flow.Flow

/**
 * Defines the data contract for managing game data in a local data source.
 */
interface LocalGameService {

    /**
     * Observe all of the [Game] entities for the given [matchId].
     */
    fun getGamesForMatch(matchId: String): Flow<List<Game>>

    /**
     * Insert a collection of [games] into our local data source.
     */
    suspend fun insertGames(games: List<Game>)
}
