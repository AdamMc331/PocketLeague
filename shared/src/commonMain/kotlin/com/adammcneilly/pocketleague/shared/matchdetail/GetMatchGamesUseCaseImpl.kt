package com.adammcneilly.pocketleague.shared.matchdetail

import com.adammcneilly.pocketleague.shared.data.DataState
import com.adammcneilly.pocketleague.shared.data.models.MatchGamesRequest
import com.adammcneilly.pocketleague.shared.data.repositories.GameRepository
import com.adammcneilly.pocketleague.shared.models.Game
import kotlinx.coroutines.flow.Flow

/**
 * A concrete implementation of [GetMatchGamesUseCase] to request the games from the given [repository].
 */
class GetMatchGamesUseCaseImpl(
    private val repository: GameRepository,
) : GetMatchGamesUseCase {

    override fun invoke(matchId: String): Flow<DataState<List<Game>>> {
        val request = MatchGamesRequest(
            matchId = matchId,
        )

        return repository.fetchGamesForMatch(request)
    }
}
