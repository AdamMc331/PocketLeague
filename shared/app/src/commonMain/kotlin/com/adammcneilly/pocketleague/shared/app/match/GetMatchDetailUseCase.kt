package com.adammcneilly.pocketleague.shared.app.match

import com.adammcneilly.pocketleague.core.datetime.TimeProvider
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.toDetailDisplayModel
import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.data.match.api.MatchListRequest
import com.adammcneilly.pocketleague.data.match.api.MatchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull

/**
 * Request detailed information about a specific match.
 */
class GetMatchDetailUseCase(
    private val matchRepository: MatchRepository,
    private val timeProvider: TimeProvider,
) {
    fun invoke(
        matchId: String,
    ): Flow<MatchDetailDisplayModel> {
        val request = MatchListRequest.Id(Match.Id(matchId))

        return matchRepository
            .stream(request)
            .mapNotNull { matchList ->
                matchList.firstOrNull()
            }
            .map { match ->
                match.toDetailDisplayModel(timeProvider)
            }
    }
}
