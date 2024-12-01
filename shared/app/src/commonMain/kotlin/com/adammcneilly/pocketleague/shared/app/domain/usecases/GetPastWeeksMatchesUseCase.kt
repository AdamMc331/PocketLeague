package com.adammcneilly.pocketleague.shared.app.domain.usecases

import com.adammcneilly.pocketleague.shared.app.core.datetime.TimeProvider
import com.adammcneilly.pocketleague.shared.app.core.models.Match
import com.adammcneilly.pocketleague.shared.app.data.match.MatchListRequest
import com.adammcneilly.pocketleague.shared.app.data.match.MatchRepository
import kotlinx.coroutines.flow.Flow

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

        return matchRepository.getMatches(request)
    }

    companion object {
        private const val DAYS_PER_WEEK = 7
    }
}
