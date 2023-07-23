package com.adammcneilly.pocketleague.data.match

import com.adammcneilly.pocketleague.core.models.Match
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock
import kotlin.time.Duration.Companion.days

/**
 * Return an observable type of matches for the past week.
 */
class GetPastWeeksMatchesUseCase(
    private val matchRepository: MatchRepository,
    private val clock: Clock,
) {
    /**
     *  @see[GetPastWeeksMatchesUseCase]
     */
    fun getPastWeeksMatches(): Flow<List<Match>> {
        return matchRepository
            .getMatchesInDateRange(
                startDateUTC = clock.now().minus(7.days).toString(),
                endDateUTC = clock.now().toString(),
            )
            .map { matchList ->
                matchList.sortedByDescending(Match::dateUTC)
            }
    }
}
