package com.adammcneilly.pocketleague.data.player

import com.adammcneilly.pocketleague.core.models.Player
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart

/**
 * An offline first implementation of [PlayerRepository] that uses [localDataSource] as our source of truth
 * and syncs that info with [remoteDataSource] whenever requested.
 */
class OfflineFirstPlayerRepository(
    private val localDataSource: LocalPlayerService,
    private val remoteDataSource: RemotePlayerService,
) : PlayerRepository {
    override fun getPlayersForTeam(
        teamId: String,
    ): Flow<List<Player>> {
        return localDataSource
            .getPlayersForTeam(teamId)
            .onStart {
                val remotePlayers = remoteDataSource.fetchPlayersForTeam(teamId)

                remotePlayers.fold(
                    onSuccess = { players ->
                        localDataSource.insertPlayers(players)
                    },
                    onFailure = { error ->
                        println("Unable to fetch players for team: $error")
                    },
                )
            }
    }
}
