package com.adammcneilly.pocketleague.shared.data.remote.octanegg.services

import com.adammcneilly.pocketleague.shared.data.DataState
import com.adammcneilly.pocketleague.shared.data.models.MatchGamesRequest
import com.adammcneilly.pocketleague.shared.data.remote.octanegg.OctaneGGAPIClient
import com.adammcneilly.pocketleague.shared.data.remote.octanegg.OctaneGGEndpoints
import com.adammcneilly.pocketleague.shared.data.remote.octanegg.mappers.toGame
import com.adammcneilly.pocketleague.shared.data.remote.octanegg.models.OctaneGGGame
import com.adammcneilly.pocketleague.shared.data.remote.octanegg.models.OctaneGGGameListResponse
import com.adammcneilly.pocketleague.shared.data.repositories.GameRepository
import com.adammcneilly.pocketleague.shared.models.Game
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
            val apiResult = apiClient.getResponse<OctaneGGGameListResponse>(
                endpoint = OctaneGGEndpoints.gamesByMatchEndpoint(request.matchId),
            )

            val mappedResult = when (apiResult) {
                is DataState.Loading -> DataState.Loading
                is DataState.Success -> {
                    val mappedGames = apiResult.data.games?.map(OctaneGGGame::toGame).orEmpty()

                    DataState.Success(mappedGames)
                }
                is DataState.Error -> {
                    DataState.Error(apiResult.error)
                }
            }

            emit(mappedResult)
        }
    }
}
