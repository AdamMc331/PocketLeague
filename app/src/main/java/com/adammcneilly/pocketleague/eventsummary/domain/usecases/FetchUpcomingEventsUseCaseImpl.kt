package com.adammcneilly.pocketleague.eventsummary.domain.usecases

import com.adammcneilly.pocketleague.core.models.EventSummary
import com.adammcneilly.pocketleague.core.models.PLResult
import com.adammcneilly.pocketleague.eventsummary.EventSummaryService
import javax.inject.Inject

/**
 * A concrete implementation of [FetchUpcomingEventsUseCase] that fetches events via the supplied [service].
 */
class FetchUpcomingEventsUseCaseImpl @Inject constructor(
    private val service: EventSummaryService,
) : FetchUpcomingEventsUseCase {

    override suspend fun invoke(): PLResult<List<EventSummary>> {
        val leagueSlug = "rlcs-2021-22-1"

        return service.fetchUpcomingEventSummaries(
            leagueSlug = leagueSlug,
        )
    }
}
