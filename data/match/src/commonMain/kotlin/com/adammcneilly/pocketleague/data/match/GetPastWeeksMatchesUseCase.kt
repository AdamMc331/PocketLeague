package com.adammcneilly.pocketleague.data.match

import com.adammcneilly.pocketleague.core.datetime.TimeProvider
import com.adammcneilly.pocketleague.core.models.Match
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val DAYS_PER_WEEK = 7

/**
 * Return an observable type of matches for the past week.
 */
class GetPastWeeksMatchesUseCase(
    private val matchRepository: MatchRepository,
    private val timeProvider: TimeProvider,
) {
    /**
     *  @see[GetPastWeeksMatchesUseCase]
     */
    fun getPastWeeksMatches(): Flow<List<Match>> {
        return matchRepository
            .getMatchesInDateRange(
                startDateUTC = timeProvider.daysAgo(DAYS_PER_WEEK),
                endDateUTC = timeProvider.now(),
            )
            .map { matchList ->
                matchList.sortedByDescending(Match::dateUTC)
            }
    }
}
