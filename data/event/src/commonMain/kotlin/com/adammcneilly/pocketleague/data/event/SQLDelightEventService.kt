package com.adammcneilly.pocketleague.data.event

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.data.local.sqldelight.PocketLeagueDB
import com.adammcneilly.pocketleague.data.local.sqldelight.mappers.toEvent
import com.adammcneilly.pocketleague.data.local.sqldelight.mappers.toEventStage
import com.adammcneilly.pocketleague.data.local.sqldelight.mappers.toLocalEvent
import com.adammcneilly.pocketleague.data.local.sqldelight.mappers.toLocalEventStage
import com.adammcneilly.pocketleague.data.local.sqldelight.mappers.toLocalTeam
import com.adammcneilly.pocketleague.data.local.sqldelight.mappers.toTeam
import com.adammcneilly.pocketleague.data.local.sqldelight.util.asFlowList
import com.adammcneilly.pocketleague.data.local.sqldelight.util.asFlowSingle
import com.adammcneilly.pocketleague.sqldelight.LocalEvent
import com.adammcneilly.pocketleague.sqldelight.LocalEventParticipant
import com.adammcneilly.pocketleague.sqldelight.LocalEventStage
import com.adammcneilly.pocketleague.sqldelight.LocalTeam
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

/**
 * An implementation of [EventRepository] that requests all data from
 * the local [database].
 */
class SQLDelightEventService(
    private val database: PocketLeagueDB,
) : LocalEventService {

    override fun getUpcomingEvents(): Flow<List<Event>> {
        return database
            .localEventQueries
            .selectUpcoming()
            .asFlowList(LocalEvent::toEvent)
    }

    override fun getEvent(eventId: Event.Id): Flow<Event> {
        val eventFlow = database
            .localEventQueries
            .selectById(eventId.id)
            .asFlowSingle(LocalEvent::toEvent)

        val stagesFlow = database
            .localEventStageQueries
            .selectAllForEvent(eventId.id)
            .asFlowList(LocalEventStage::toEventStage)

        return eventFlow
            .combine(stagesFlow) { event, stages ->
                event.copy(
                    stages = stages,
                )
            }
    }

    override fun getEventParticipants(eventId: Event.Id): Flow<List<Team>> {
        return database
            .localEventParticipantQueries
            .selectParticipantsForEvent(eventId.id)
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

    override suspend fun insertEventParticipants(teams: List<Team>, eventId: Event.Id) {
        teams.forEach { team ->
            database
                .localTeamQueries
                .insertFullTeamObject(team.toLocalTeam())

            database
                .localEventParticipantQueries
                .insertEventParticipant(
                    LocalEventParticipant(
                        eventId = eventId.id,
                        teamId = team.id,
                    ),
                )
        }
    }
}
