package com.adammcneilly.pocketleague.data.game

import com.adammcneilly.pocketleague.core.models.Game
import com.adammcneilly.pocketleague.data.octanegg.OctaneGGAPIClient
import com.adammcneilly.pocketleague.data.octanegg.models.OctaneGGGame
import com.adammcneilly.pocketleague.data.octanegg.models.OctaneGGGameListResponse
import com.adammcneilly.pocketleague.data.octanegg.models.toGame
import com.adammcneilly.pocketleague.data.remote.BaseKTORClient

/**
 * A concrete implementation of [GameService] that requests information via the supplied
 * [apiClient].
 */
class OctaneGGGameService(
    private val apiClient: BaseKTORClient,
) : GameService {

    constructor() : this(OctaneGGAPIClient)

    override suspend fun fetchGamesForMatch(request: MatchGamesRequest): Result<List<Game>> {
        val matchId = request.matchId

        return apiClient.getResponse<OctaneGGGameListResponse>(
            endpoint = "$MATCHES_ENDPOINT/${matchId.id}/games",
        ).map { gameListResponse ->
            val mappedGames = gameListResponse.games?.map(OctaneGGGame::toGame)

            val sortedGames = mappedGames?.sortedBy(Game::number)

            sortedGames.orEmpty()
        }
    }

    companion object {
        private const val MATCHES_ENDPOINT = "/matches"
    }
}
