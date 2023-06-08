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
    val eventsByName: MutableMap<String, Result<Event>> = mutableMapOf(
        TestModel.event.name to Result.success(TestModel.event),
    )
    val eventParticipantsByEventId: MutableMap<String, Result<List<Team>>> = mutableMapOf(
        TestModel.event.id to Result.success(listOf(TestModel.team)),
    )

    private var upcomingEventsRequestCount = 0
    private var ongoingEventsRequestCount = 0
    private var eventParticipantsRequestCount = 0

    override suspend fun getUpcomingEvents(): Result<List<Event>> {
        upcomingEventsRequestCount += 1
        return this.upcomingEvents
    }

    override suspend fun getEventById(eventId: String): Result<Event> {
        return this.eventsById[eventId]!!
    }

    override suspend fun getEventParticipants(eventId: String): Result<List<Team>> {
        eventParticipantsRequestCount += 1
        return this.eventParticipantsByEventId[eventId]!!
    }

    override suspend fun getOngoingEvents(): Result<List<Event>> {
        ongoingEventsRequestCount += 1
        return this.ongoingEvents
    }

    override suspend fun getEventByName(eventName: String): Result<Event> {
        return this.eventsByName[eventName]!!
    }

    fun verifyUpcomingEventsRequested(count: Int) {
        requireInvocationCount(count, upcomingEventsRequestCount)
    }

    fun verifyOngoingEventsRequested(count: Int) {
        requireInvocationCount(count, ongoingEventsRequestCount)
    }

    fun verifyEventParticipantsRequested(count: Int) {
        requireInvocationCount(count, eventParticipantsRequestCount)
    }

    private fun requireInvocationCount(
        expectedCount: Int,
        invocationCount: Int,
    ) {
        require(expectedCount == invocationCount) {
            "Expected $expectedCount invocations, was: $invocationCount"
        }
    }
}
