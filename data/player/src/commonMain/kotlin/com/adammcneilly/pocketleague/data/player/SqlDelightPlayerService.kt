package com.adammcneilly.pocketleague.data.player

import com.adammcneilly.pocketleague.core.models.Player
import com.adammcneilly.pocketleague.data.local.sqldelight.PocketLeagueDB
import com.adammcneilly.pocketleague.data.local.sqldelight.mappers.toLocalPlayer
import com.adammcneilly.pocketleague.data.local.sqldelight.mappers.toPlayer
import com.adammcneilly.pocketleague.data.local.sqldelight.util.asFlowList
import kotlinx.coroutines.flow.Flow

/**
 * Implementation of a [LocalPlayerService] that requests data from the given [database].
 */
class SqlDelightPlayerService(
    private val database: PocketLeagueDB,
) : LocalPlayerService {
    override fun getPlayersForTeam(teamId: String): Flow<List<Player>> {
        return database
            .localPlayerQueries
            .selectByTeam(teamId)
            .asFlowList { localPlayer ->
                // Need to create mapper
                localPlayer.toPlayer()
            }
    }

    override suspend fun insertPlayers(players: List<Player>) {
        database.transaction {
            players.forEach { player ->
                database
                    .localPlayerQueries
                    .insertFullPlayerObject(player.toLocalPlayer())
            }
        }
    }
}
