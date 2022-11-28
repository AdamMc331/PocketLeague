package com.adammcneilly.pocketleague.data.event.test

import com.adammcneilly.pocketleague.core.models.DataState
import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.core.models.test.TestModel
import com.adammcneilly.pocketleague.core.models.test.event
import com.adammcneilly.pocketleague.core.models.test.team
import com.adammcneilly.pocketleague.data.event.EventListRequest
import com.adammcneilly.pocketleague.data.event.EventService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeEventService : EventService {

    var eventListResponse: DataState<List<Event>> = DataState.Success(
        listOf(TestModel.event),
    )

    var eventResponse: DataState<Event> = DataState.Success(
        TestModel.event,
    )

    var eventParticipants: DataState<List<Team>> = DataState.Success(
        listOf(TestModel.team),
    )

    override suspend fun fetchEvents(request: EventListRequest): DataState<List<Event>> {
        return eventListResponse
    }

    override suspend fun fetchEvent(eventId: String): DataState<Event> {
        return eventResponse
    }

    override suspend fun fetchEventParticipants(eventId: String): DataState<List<Team>> {
        return eventParticipants
    }

    override fun getUpcomingEvents(): Flow<List<Event>> {
        return flowOf(listOf(TestModel.event))
    }

    override fun getEvent(eventId: String): Flow<Event> {
        return flowOf(TestModel.event)
    }

    override suspend fun sync() {
        // No op in test scenarios.
    }
}
