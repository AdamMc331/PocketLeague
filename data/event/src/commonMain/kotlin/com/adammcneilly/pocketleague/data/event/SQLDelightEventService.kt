package com.adammcneilly.pocketleague.data.event

import com.adammcneilly.pocketleague.core.datetime.defaultClock
import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.SwissStageTeamResult
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
import kotlinx.datetime.Clock

/**
 * An implementation of [EventRepository] that requests all data from
 * the local [database].
 */
class SQLDelightEventService(
    private val database: PocketLeagueDB,
    private val clock: Clock,
) : LocalEventService {

    constructor(database: PocketLeagueDB) : this(database, defaultClock())

    override fun getUpcomingEvents(): Flow<List<Event>> {
        return database
            .localEventQueries
            .selectUpcoming(
                startDateUtc = clock.now().toString(),
            )
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
            .selectOngoing(
                startDateUtc = clock.now().toString(),
                endDateUtc = clock.now().toString(),
            )
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

    override fun getSwissStageResults(eventId: String, stageId: String): Flow<List<SwissStageTeamResult>> {
        return database
            .localEventStageQueries
            .selectSwissStageResults(eventId, stageId)
            .asFlowList { dbResult ->
                SwissStageTeamResult(
                    team = Team(
                        id = dbResult.id,
                        name = dbResult.name,
                        lightThemeImageURL = dbResult.lightImageURL,
                        darkThemeImageURL = dbResult.darkImageURL,
                        isFavorite = dbResult.isFavorite,
                        isActive = dbResult.isActive ?: false,
                        regionName = dbResult.region.orEmpty(),
                    ),
                    matchWins = dbResult.matchWins?.toInt() ?: 0,
                    matchLosses = dbResult.matchLosses?.toInt() ?: 0,
                    gameWins = dbResult.gameWins?.toInt() ?: 0,
                    gameLosses = dbResult.gameLosses?.toInt() ?: 0,
                    eventId = eventId,
                    stageId = stageId,
                )
            }
    }
}
