package com.adammcneilly.pocketleague.data.event.test

import com.adammcneilly.pocketleague.core.models.DataState
import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.core.models.test.TestModel
import com.adammcneilly.pocketleague.core.models.test.event
import com.adammcneilly.pocketleague.core.models.test.team
import com.adammcneilly.pocketleague.data.event.RemoteEventService

class FakeRemoteEventService : RemoteEventService {

    var upcomingEvents: DataState<List<Event>> = DataState.Success(listOf(TestModel.event))
    var ongoingEvents: DataState<List<Event>> = DataState.Success(listOf(TestModel.event))
    val eventsById: MutableMap<String, DataState<Event>> = mutableMapOf(
        TestModel.event.id to DataState.Success(TestModel.event),
    )
    val eventParticipantsByEventId: MutableMap<String, DataState<List<Team>>> = mutableMapOf(
        TestModel.event.id to DataState.Success(listOf(TestModel.team)),
    )

    override suspend fun getUpcomingEvents(): DataState<List<Event>> {
        return this.upcomingEvents
    }

    override suspend fun getEvent(eventId: String): DataState<Event> {
        return this.eventsById[eventId]!!
    }

    override suspend fun getEventParticipants(eventId: String): DataState<List<Team>> {
        return this.eventParticipantsByEventId[eventId]!!
    }

    override suspend fun getOngoingEvents(): DataState<List<Event>> {
        return this.ongoingEvents
    }
}
