package com.adammcneilly.pocketleague.data.player

import com.adammcneilly.pocketleague.core.models.Player

/**
 * Defines the data contract for requesting remote information about players.
 */
interface RemotePlayerService {

    suspend fun fetchPlayersForTeam(teamId: String): Result<List<Player>>
}
