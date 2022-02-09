package com.adammcneilly.pocketleague.eventsummary.domain.usecases

import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.core.models.EventSummary
import com.adammcneilly.pocketleague.event.data.EventService
import javax.inject.Inject

/**
 * A concrete implementation of [FetchUpcomingEventsUseCase] that fetches events via the supplied [service].
 */
class FetchUpcomingEventsUseCaseImpl @Inject constructor(
    private val service: EventService,
) : FetchUpcomingEventsUseCase {

    override suspend fun invoke(): Result<List<com.adammcneilly.pocketleague.core.models.EventSummary>> {
        val leagueSlug = "rlcs-2021-22-1"

        return service.fetchUpcomingEvents(
            leagueSlug = leagueSlug,
        )
    }
}
