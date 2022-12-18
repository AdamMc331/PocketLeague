package com.adammcneilly.pocketleague.data.event

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.data.local.PocketLeagueDB
import com.adammcneilly.pocketleague.data.local.mappers.toEvent
import com.adammcneilly.pocketleague.data.local.mappers.toLocalEvent
import com.adammcneilly.pocketleague.data.local.mappers.toLocalEventStage
import com.adammcneilly.pocketleague.data.local.mappers.toLocalTeam
import com.adammcneilly.pocketleague.data.local.mappers.toTeam
import com.adammcneilly.pocketleague.data.local.util.asFlowList
import com.adammcneilly.pocketleague.sqldelight.LocalEvent
import com.adammcneilly.pocketleague.sqldelight.LocalEventParticipant
import com.adammcneilly.pocketleague.sqldelight.LocalTeam
import kotlinx.coroutines.flow.Flow

/**
 * An implementation of [EventRepository] that requests all data from
 * the local [database].
 */
class SQLDelightEventRepository(
    private val database: PocketLeagueDB,
) : EventRepository {

    override fun getUpcomingEvents(): Flow<List<Event>> {
        return database
            .localEventQueries
            .selectUpcoming()
            .asFlowList(LocalEvent::toEvent)
    }

    override fun getEvent(eventId: String): Flow<Event> {
        TODO("Not yet implemented")
    }

    override fun getEventParticipants(eventId: String): Flow<List<Team>> {
        return database
            .localEventParticipantQueries
            .selectParticipantsForEvent(eventId)
            .asFlowList(LocalTeam::toTeam)
    }

    override fun getOngoingEvents(): Flow<List<Event>> {
        return database
            .localEventQueries
            .selectOngoing()
            .asFlowList(LocalEvent::toEvent)
    }

    override suspend fun insertEvents(events: List<Event>) {
        events.forEach { event ->
            database
                .localEventQueries
                .insertFullEventObject(event.toLocalEvent())

            event.stages.forEach { stage ->
                database
                    .localEventStageQueries
                    .insertFullEventStage(stage.toLocalEventStage(event.id))
            }
        }
    }

    override suspend fun insertEventParticipants(teams: List<Team>, eventId: String) {
        teams.forEach { team ->
            database
                .localTeamQueries
                .insertFullTeamObject(team.toLocalTeam())

            database
                .localEventParticipantQueries
                .insertEventParticipant(
                    LocalEventParticipant(
                        eventId = eventId,
                        teamId = team.id,
                    )
                )
        }
    }
}
