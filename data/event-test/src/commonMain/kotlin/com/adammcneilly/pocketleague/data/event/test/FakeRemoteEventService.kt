package com.adammcneilly.pocketleague.data.event.test

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.core.models.test.TestModel
import com.adammcneilly.pocketleague.core.models.test.event
import com.adammcneilly.pocketleague.core.models.test.team
import com.adammcneilly.pocketleague.data.event.RemoteEventService

class FakeRemoteEventService : RemoteEventService {

    var upcomingEvents: Result<List<Event>> = Result.success(listOf(TestModel.event))
    var ongoingEvents: Result<List<Event>> = Result.success(listOf(TestModel.event))
    val eventsById: MutableMap<String, Result<Event>> = mutableMapOf(
        TestModel.event.id to Result.success(TestModel.event),
    )
    val eventParticipantsByEventId: MutableMap<String, Result<List<Team>>> = mutableMapOf(
        TestModel.event.id to Result.success(listOf(TestModel.team)),
    )

    override suspend fun getUpcomingEvents(): Result<List<Event>> {
        return this.upcomingEvents
    }

    override suspend fun getEventById(eventId: String): Result<Event> {
        return this.eventsById[eventId]!!
    }

    override suspend fun getEventParticipants(eventId: String): Result<List<Team>> {
        return this.eventParticipantsByEventId[eventId]!!
    }

    override suspend fun getOngoingEvents(): Result<List<Event>> {
        return this.ongoingEvents
    }
}
