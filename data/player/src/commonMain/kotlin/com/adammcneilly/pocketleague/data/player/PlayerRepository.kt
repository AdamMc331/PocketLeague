package com.adammcneilly.pocketleague.data.player

import com.adammcneilly.pocketleague.core.models.Player
import kotlinx.coroutines.flow.Flow

/**
 * Defines the most outward facing part of the player data layer. The implementation of this repository
 * will combine multiple data sources if necessary.
 */
interface PlayerRepository {
    /**
     * Observe a list of players for the given [teamId].
     */
    fun getPlayersForTeam(
        teamId: String,
    ): Flow<List<Player>>
}
