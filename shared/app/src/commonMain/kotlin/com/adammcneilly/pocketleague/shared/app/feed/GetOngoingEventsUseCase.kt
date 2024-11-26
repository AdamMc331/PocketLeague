package com.adammcneilly.pocketleague.shared.app.feed

import com.adammcneilly.pocketleague.core.datetime.TimeProvider
import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.data.event.api.EventListRequest
import com.adammcneilly.pocketleague.data.event.api.EventRepository
import kotlinx.coroutines.flow.Flow

/**
 * Return an observable type of events that are currently happening on today's date.
 */
class GetOngoingEventsUseCase(
    private val eventRepository: EventRepository,
    private val timeProvider: TimeProvider,
) {
    /**
     * @see [GetOngoingEventsUseCase]
     */
    fun invoke(): Flow<List<Event>> {
        val request = EventListRequest.OnDate(
            dateUtc = timeProvider.now(),
        )

        return eventRepository.stream(request)
    }
}
