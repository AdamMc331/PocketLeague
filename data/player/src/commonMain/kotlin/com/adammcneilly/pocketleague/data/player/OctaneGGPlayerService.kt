package com.adammcneilly.pocketleague.data.player

import com.adammcneilly.pocketleague.core.models.Player
import com.adammcneilly.pocketleague.data.octanegg.models.OctaneGGPlayer
import com.adammcneilly.pocketleague.data.octanegg.models.OctaneGGPlayerListResponse
import com.adammcneilly.pocketleague.data.octanegg.models.toPlayer
import com.adammcneilly.pocketleague.data.remote.BaseKTORClient

/**
 * Implementation of a [RemotePlayerService] that requests data from the given [apiClient].
 */
class OctaneGGPlayerService(
    private val apiClient: BaseKTORClient,
) : RemotePlayerService {
    override suspend fun fetchPlayersForTeam(teamId: String): Result<List<Player>> {
        return apiClient.getResponse<OctaneGGPlayerListResponse>(
            endpoint = "/players",
            params =
                mapOf(
                    "team" to teamId,
                ),
        ).map { playerListResponse ->
            playerListResponse.players
                ?.map(OctaneGGPlayer::toPlayer)
                .orEmpty()
        }
    }
}
