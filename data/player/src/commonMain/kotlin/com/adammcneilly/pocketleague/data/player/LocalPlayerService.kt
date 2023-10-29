package com.adammcneilly.pocketleague.data.player

import com.adammcneilly.pocketleague.core.models.Player
import kotlinx.coroutines.flow.Flow

/**
 * Defines the data contract for a local data source of player information.
 */
interface LocalPlayerService {
    /**
     * Observe a list of players for the given [teamId].
     */
    fun getPlayersForTeam(
        teamId: String,
    ): Flow<List<Player>>

    /**
     * Store a list of players in a local data source.
     */
    suspend fun insertPlayers(
        players: List<Player>,
    )
}
