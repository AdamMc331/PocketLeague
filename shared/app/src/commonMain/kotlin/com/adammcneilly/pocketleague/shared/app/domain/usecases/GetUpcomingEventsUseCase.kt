package com.adammcneilly.pocketleague.shared.app.domain.usecases

import com.adammcneilly.pocketleague.shared.app.core.datetime.TimeProvider
import com.adammcneilly.pocketleague.shared.app.core.models.Event
import kotlinx.coroutines.flow.Flow

/**
 * Return an observable type of events that are upcoming from today's date.
 */
class GetUpcomingEventsUseCase(
    private val eventRepository: EventRepository,
    private val timeProvider: TimeProvider,
) {
    /**
     * @see [GetUpcomingEventsUseCase]
     */
    fun invoke(): Flow<List<Event>> {
        val request = EventListRequest.AfterDate(
            dateUtc = timeProvider.now(),
        )

        return eventRepository.stream(request)
    }
}
