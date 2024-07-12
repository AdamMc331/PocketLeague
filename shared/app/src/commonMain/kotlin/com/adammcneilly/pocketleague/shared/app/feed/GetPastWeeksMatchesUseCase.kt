package com.adammcneilly.pocketleague.shared.app.feed
import com.adammcneilly.pocketleague.core.datetime.TimeProvider
import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.data.match.api.MatchListRequest
import com.adammcneilly.pocketleague.data.match.api.MatchRepository
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
            teamId = null,
        )

        return matchRepository.stream(request)
    }
}
