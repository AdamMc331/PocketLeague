package com.adammcneilly.pocketleague.data.event.test

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.core.models.test.TestModel
import com.adammcneilly.pocketleague.core.models.test.event
import com.adammcneilly.pocketleague.core.models.test.team
import com.adammcneilly.pocketleague.data.event.LocalEventService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeLocalEventService : LocalEventService {

    var upcomingEvents: List<Event> = listOf(TestModel.event)
    var ongoingEvents: List<Event> = listOf(TestModel.event)
    val eventsById: MutableMap<String, Event> = mutableMapOf(
        TestModel.event.id to TestModel.event,
    )
    val eventParticipantsByEventId: MutableMap<String, List<Team>> = mutableMapOf(
        TestModel.event.id to listOf(TestModel.team),
    )

    private val insertedEvents: MutableList<Event> = mutableListOf()
    private val insertedEventParticipantsByEventId: MutableMap<String, List<Team>> = mutableMapOf()

    override fun getUpcomingEvents(): Flow<List<Event>> {
        return flowOf(upcomingEvents)
    }

    override fun getEvent(eventId: String): Flow<Event> {
        return flowOf(eventsById[eventId]!!)
    }

    override fun getEventParticipants(eventId: String): Flow<List<Team>> {
        return flowOf(eventParticipantsByEventId[eventId]!!)
    }

    override fun getOngoingEvents(): Flow<List<Event>> {
        return flowOf(ongoingEvents)
    }

    override suspend fun insertEvents(events: List<Event>) {
        insertedEvents.addAll(events)
    }

    override suspend fun insertEventParticipants(teams: List<Team>, eventId: String) {
        insertedEventParticipantsByEventId[eventId] = teams
    }

    fun assertEventsInserted(events: List<Event>) {
        // Need to replace with truthish lib?
        assert(insertedEvents.containsAll(events))
    }

    fun assertEventParticipantsInserted(
        teams: List<Team>,
        eventId: String,
    ) {
        val insertedTeams = insertedEventParticipantsByEventId[eventId]!!

        // Need to replace with truthish lib?
        assert(insertedTeams == teams)
    }
}
