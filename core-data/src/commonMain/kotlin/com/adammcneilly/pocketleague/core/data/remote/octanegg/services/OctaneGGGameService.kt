package com.adammcneilly.pocketleague.core.data.remote.octanegg.services

import com.adammcneilly.pocketleague.core.data.DataState
import com.adammcneilly.pocketleague.core.data.models.MatchGamesRequest
import com.adammcneilly.pocketleague.core.data.remote.octanegg.OctaneGGAPIClient
import com.adammcneilly.pocketleague.core.data.remote.octanegg.OctaneGGEndpoints
import com.adammcneilly.pocketleague.core.data.remote.octanegg.mappers.toGame
import com.adammcneilly.pocketleague.core.data.remote.octanegg.models.OctaneGGGame
import com.adammcneilly.pocketleague.core.data.remote.octanegg.models.OctaneGGGameListResponse
import com.adammcneilly.pocketleague.core.data.repositories.GameRepository
import com.adammcneilly.pocketleague.core.models.Game
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * A concrete implementation of [GameRepository] that requests information via the supplied
 * [apiClient].
 */
class OctaneGGGameService(
    private val apiClient: OctaneGGAPIClient = OctaneGGAPIClient(),
) : GameRepository {

    override fun fetchGamesForMatch(request: MatchGamesRequest): Flow<DataState<List<Game>>> {
        return flow {
            emit(DataState.Loading)

            val apiResult = apiClient.getResponse<OctaneGGGameListResponse>(
                endpoint = OctaneGGEndpoints.gamesByMatchEndpoint(request.matchId),
            )

            val mappedResult = when (apiResult) {
                is DataState.Loading -> DataState.Loading
                is DataState.Success -> {
                    val mappedGames = apiResult.data.games?.map(OctaneGGGame::toGame).orEmpty()

                    val sortedGames = mappedGames.sortedBy(Game::number)

                    DataState.Success(sortedGames)
                }
                is DataState.Error -> {
                    DataState.Error(apiResult.error)
                }
            }

            emit(mappedResult)
        }
    }
}
