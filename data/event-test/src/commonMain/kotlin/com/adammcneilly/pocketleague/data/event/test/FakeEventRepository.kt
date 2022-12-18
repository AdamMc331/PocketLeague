package com.adammcneilly.pocketleague.data.event.test

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.data.event.EventRepository
import kotlinx.coroutines.flow.Flow

class FakeEventRepository : EventRepository {

    override fun getUpcomingEvents(): Flow<List<Event>> {
        TODO("Not yet implemented")
    }

    override fun getEvent(eventId: String): Flow<Event> {
        TODO("Not yet implemented")
    }

    override fun getEventParticipants(eventId: String): Flow<List<Team>> {
        TODO("Not yet implemented")
    }

    override fun getOngoingEvents(): Flow<List<Event>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertEvents(events: List<Event>) {
        TODO("Not yet implemented")
    }

    override suspend fun insertEventParticipants(teams: List<Team>, eventId: String) {
        TODO("Not yet implemented")
    }
}
