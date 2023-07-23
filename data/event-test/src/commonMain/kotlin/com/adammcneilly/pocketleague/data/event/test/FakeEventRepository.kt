package com.adammcneilly.pocketleague.data.event.test

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.SwissStageTeamResult
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.core.models.test.TestModel
import com.adammcneilly.pocketleague.core.models.test.event
import com.adammcneilly.pocketleague.core.models.test.team
import com.adammcneilly.pocketleague.data.event.EventRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeEventRepository : EventRepository {

    var upcomingEvents: List<Event> = listOf(TestModel.event)
    var ongoingEvents: List<Event> = listOf(TestModel.event)
    val eventsById: MutableMap<Event.Id, Event> = mutableMapOf(
        TestModel.event.id to TestModel.event,
    )
    val eventParticipantsByEventId: MutableMap<Event.Id, List<Team>> = mutableMapOf(
        TestModel.event.id to listOf(TestModel.team),
    )

    override fun getUpcomingEvents(): Flow<List<Event>> {
        return flowOf(upcomingEvents)
    }

    override fun getEvent(eventId: Event.Id): Flow<Event> {
        return flowOf(eventsById[eventId]!!)
    }

    override fun getEventParticipants(eventId: Event.Id): Flow<List<Team>> {
        return flowOf(eventParticipantsByEventId[eventId]!!)
    }

    override fun getOngoingEvents(): Flow<List<Event>> {
        return flowOf(ongoingEvents)
    }

    override fun getSwissStageResults(eventId: String, stageId: String): Flow<List<SwissStageTeamResult>> {
        TODO("Not yet implemented")
    }
}
