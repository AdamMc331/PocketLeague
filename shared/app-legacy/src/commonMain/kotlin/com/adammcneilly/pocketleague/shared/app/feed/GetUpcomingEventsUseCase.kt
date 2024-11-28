package com.adammcneilly.pocketleague.shared.app.feed

import com.adammcneilly.pocketleague.core.datetime.TimeProvider
import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.data.event.api.EventListRequest
import com.adammcneilly.pocketleague.data.event.api.EventRepository
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
