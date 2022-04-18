package com.adammcneilly.pocketleague.shared.matchlist

import com.adammcneilly.pocketleague.shared.data.DataResult
import com.adammcneilly.pocketleague.shared.data.models.MatchListRequest
import com.adammcneilly.pocketleague.shared.data.repositories.MatchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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

    override fun invoke(numDays: Int): Flow<GetRecentMatchesUseCase.Result> {
        val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

        val startDate = today.date.minus(numDays, DateTimeUnit.DAY)
            .atStartOfDayIn(TimeZone.currentSystemDefault())
            .toLocalDateTime(TimeZone.currentSystemDefault())

        val request = MatchListRequest(
            after = startDate,
            before = today,
        )

        return repository.fetchMatches(request).map { repoResult ->
            when (repoResult) {
                is DataResult.Success -> {
                    GetRecentMatchesUseCase.Result.Success(repoResult.data)
                }
                is DataResult.Error -> {
                    GetRecentMatchesUseCase.Result.Error(repoResult.error)
                }
            }
        }
    }
}
