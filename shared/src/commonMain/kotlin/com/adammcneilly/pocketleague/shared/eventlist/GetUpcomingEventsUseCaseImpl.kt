package com.adammcneilly.pocketleague.shared.eventlist

import com.adammcneilly.pocketleague.shared.data.DataState
import com.adammcneilly.pocketleague.shared.data.models.EventListRequest
import com.adammcneilly.pocketleague.shared.data.repositories.EventRepository
import com.adammcneilly.pocketleague.shared.models.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

/**
 * A concrete implementation of [GetUpcomingEventsUseCase] that will return information from
 * the supplied [repository].
 */
class GetUpcomingEventsUseCaseImpl(
    private val repository: EventRepository,
) : GetUpcomingEventsUseCase {

    override fun invoke(): Flow<DataState<List<Event>>> {
        val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

        val request = EventListRequest(
            after = today,
        )

        return repository.fetchEvents(request)
    }
}
