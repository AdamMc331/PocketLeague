package com.adammcneilly.pocketleague.shared.app.domain.usecases

import com.adammcneilly.pocketleague.shared.app.core.datetime.DateTimeFormatter
import com.adammcneilly.pocketleague.shared.app.core.datetime.TimeProvider
import com.adammcneilly.pocketleague.shared.app.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.shared.app.data.match.MatchListRequest
import com.adammcneilly.pocketleague.shared.app.data.match.MatchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Return an observable type of matches for the past week.
 */
class GetPastWeeksMatchesUseCase(
    private val dateTimeFormatter: DateTimeFormatter,
    private val matchRepository: MatchRepository,
    private val timeProvider: TimeProvider,
) {
    /**
     * @see [GetPastWeeksMatchesUseCase].
     */
    fun invoke(): Flow<List<MatchDetailDisplayModel>> {
        val request = MatchListRequest.DateRange(
            startDateUTC = timeProvider.daysAgo(DAYS_PER_WEEK),
            endDateUTC = timeProvider.now(),
        )

        return matchRepository
            .getMatches(request)
            .map { matchList ->
                matchList.map { match ->
                    MatchDetailDisplayModel(
                        match = match,
                        dateTimeFormatter = dateTimeFormatter,
                        timeProvider = timeProvider,
                    )
                }
            }
    }

    companion object {
        private const val DAYS_PER_WEEK = 7
    }
}
