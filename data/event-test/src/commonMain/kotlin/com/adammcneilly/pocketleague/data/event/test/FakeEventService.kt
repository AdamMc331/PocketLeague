package com.adammcneilly.pocketleague.data.event.test

import com.adammcneilly.pocketleague.core.models.DataState
import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.core.models.test.TestModel
import com.adammcneilly.pocketleague.core.models.test.event
import com.adammcneilly.pocketleague.core.models.test.team
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

    override fun getEventParticipants(eventId: String): Flow<List<Team>> {
        return flowOf(listOf(TestModel.team))
    }

    override fun getUpcomingEvents(): Flow<List<Event>> {
        return flowOf(listOf(TestModel.event))
    }

    override fun getEvent(eventId: String): Flow<Event> {
        return flowOf(TestModel.event)
    }

    override fun getOngoingEvents(): Flow<List<Event>> {
        return flowOf(listOf(TestModel.event))
    }
}
