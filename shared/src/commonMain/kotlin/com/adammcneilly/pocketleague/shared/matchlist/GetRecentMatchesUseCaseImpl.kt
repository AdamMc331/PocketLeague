package com.adammcneilly.pocketleague.shared.matchlist

import com.adammcneilly.pocketleague.shared.data.DataState
import com.adammcneilly.pocketleague.shared.data.models.MatchListRequest
import com.adammcneilly.pocketleague.shared.data.repositories.MatchRepository
import com.adammcneilly.pocketleague.shared.models.Match
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.minus
import kotlinx.datetime.toLocalDateTime

/**
 * A concrete implementation of a [GetRecentMatchesUseCase] that requests matches
 * from the given [repository].
 */
class GetRecentMatchesUseCaseImpl(
    private val repository: MatchRepository,
) : GetRecentMatchesUseCase {

    override fun invoke(numDays: Int): Flow<DataState<List<Match>>> {
        val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

        val startDate = today.date.minus(numDays, DateTimeUnit.DAY)
            .atStartOfDayIn(TimeZone.currentSystemDefault())
            .toLocalDateTime(TimeZone.currentSystemDefault())

        val request = MatchListRequest(
            after = startDate,
            before = today,
        )

        return repository.fetchMatches(request)
    }
}
