package com.adammcneilly.pocketleague.data.player

import com.adammcneilly.pocketleague.core.models.Player

/**
 * Defines the data contract for requesting remote information about players.
 */
interface RemotePlayerService {
    /**
     * Make a single request for a list of players for the given [teamId]. Surface any errors requesting that data
     * if they occur.
     */
    suspend fun fetchPlayersForTeam(
        teamId: String,
    ): Result<List<Player>>
}
