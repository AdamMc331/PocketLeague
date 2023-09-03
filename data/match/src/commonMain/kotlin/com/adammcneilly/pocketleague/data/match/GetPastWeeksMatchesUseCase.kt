package com.adammcneilly.pocketleague.data.match

import com.adammcneilly.pocketleague.core.datetime.TimeProvider
import com.adammcneilly.pocketleague.core.models.Match
import kotlinx.coroutines.flow.Flow

private const val DAYS_PER_WEEK = 7

/**
 * Return an observable type of matches for the past week.
 */
class GetPastWeeksMatchesUseCase(
    private val timeProvider: TimeProvider,
    private val matchRepository: MatchRepository,
) {

    /**
     * @see [GetPastWeeksMatchesUseCase].
     */
    fun invoke(): Flow<List<Match>> {
        val request = MatchListRequest.DateRange(
            startDateUTC = timeProvider.daysAgo(DAYS_PER_WEEK),
            endDateUTC = timeProvider.now(),
        )

        return matchRepository.stream(request)
    }
}
