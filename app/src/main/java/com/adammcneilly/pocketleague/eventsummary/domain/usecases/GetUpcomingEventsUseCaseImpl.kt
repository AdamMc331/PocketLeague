package com.adammcneilly.pocketleague.eventsummary.domain.usecases

import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.event.data.EventService
import com.adammcneilly.pocketleague.eventsummary.domain.models.EventSummary
import javax.inject.Inject

class GetUpcomingEventsUseCaseImpl @Inject constructor(
    private val service: EventService,
) : GetUpcomingEventsUseCase {

    override suspend fun invoke(): Result<List<EventSummary>> {
        val leagueSlug = "rlcs-2021-22-1"

        return service.fetchUpcomingEvents(
            leagueSlug = leagueSlug,
        )
    }
}
