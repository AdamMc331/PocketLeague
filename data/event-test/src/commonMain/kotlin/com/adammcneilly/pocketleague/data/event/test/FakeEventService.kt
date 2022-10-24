package com.adammcneilly.pocketleague.data.event.test

import com.adammcneilly.pocketleague.core.data.DataState
import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.core.models.test.TestModel
import com.adammcneilly.pocketleague.core.models.test.team
import com.adammcneilly.pocketleague.core.models.test.testEvent
import com.adammcneilly.pocketleague.data.event.EventListRequest
import com.adammcneilly.pocketleague.data.event.EventService

class FakeEventService : EventService {

    var eventListResponse: DataState<List<Event>> = DataState.Success(
        listOf(testEvent),
    )

    var eventResponse: DataState<Event> = DataState.Success(
        testEvent,
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
}
