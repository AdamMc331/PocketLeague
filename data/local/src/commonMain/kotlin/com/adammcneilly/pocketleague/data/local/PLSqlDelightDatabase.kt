package com.adammcneilly.pocketleague.data.local

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.EventStage
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.data.local.mappers.toEvent
import com.adammcneilly.pocketleague.data.local.mappers.toLocalEvent
import com.adammcneilly.pocketleague.data.local.mappers.toLocalTeam
import com.adammcneilly.pocketleague.data.local.mappers.toTeam
import com.adammcneilly.pocketleague.sqldelight.LocalEvent
import com.adammcneilly.pocketleague.sqldelight.LocalEventStage
import com.adammcneilly.pocketleague.sqldelight.LocalTeam
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOne
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

/**
 * An implementation of [PocketLeagueDatabase] that uses SQL Delight to power the data requests.
 */
class PLSqlDelightDatabase(databaseDriver: SqlDriver) : PocketLeagueDatabase {
    private val database = PocketLeagueDB(databaseDriver)

    override fun getFavoriteTeams(): Flow<List<Team>> {
        return database.localTeamQueries
            .selectFavorites()
            .asFlow()
            .mapToList()
            .map { localTeamList ->
                localTeamList.map(LocalTeam::toTeam)
            }
    }

    override fun getAllTeams(): Flow<List<Team>> {
        return database.localTeamQueries
            .selectAll()
            .asFlow()
            .mapToList()
            .map { localTeamList ->
                localTeamList.map(LocalTeam::toTeam)
            }
    }

    override suspend fun storeTeams(teams: List<Team>) {
        database.transaction {
            teams.forEach { team ->
                database
                    .localTeamQueries
                    .insertFullTeamObject(team.toLocalTeam())
            }
        }
    }

    override fun getUpcomingEvents(): Flow<List<Event>> {
        return database.localEventQueries
            .selectUpcoming()
            .asFlow()
            .mapToList()
            .map { eventList ->
                eventList.map(LocalEvent::toEvent)
            }
    }

    override suspend fun storeEvents(events: List<Event>) {
        database.transaction {
            events.forEach { event ->
                database
                    .localEventQueries
                    .insertFullEventObject(event.toLocalEvent())

                event.stages.forEach { eventStage ->
                    database
                        .localEventStageQueries
                        .insertFullEventStage(eventStage.toLocalEventStage(event.id))
                }
            }
        }
    }

    override fun getEvent(eventId: String): Flow<Event> {
        val eventFlow = database.localEventQueries
            .selectById(eventId)
            .asFlow()
            .mapToOne()
            .map(LocalEvent::toEvent)

        val eventStageFlow = database.localEventStageQueries
            .selectAllForEvent(eventId)
            .asFlow()
            .mapToList()
            .map { localEventStageList ->
                localEventStageList.map(LocalEventStage::toEventStage)
            }

        return combine(eventFlow, eventStageFlow) { event, stageList ->
            event.copy(
                stages = stageList,
            )
        }
    }
}

private fun EventStage.toLocalEventStage(
    eventId: String
): LocalEventStage {
    return LocalEventStage(
        id = this.id,
        eventId = eventId,
        name = this.name,
        region = this.region,
        startDateUTC = this.startDateUTC,
        endDateUTC = this.endDateUTC,
        liquipedia = this.liquipedia,
        qualifier = this.qualifier,
        lan = this.lan,
    )
}

private fun LocalEventStage.toEventStage(): EventStage {
    return EventStage(
        id = this.id,
        name = this.name,
        region = this.region,
        startDateUTC = this.startDateUTC,
        endDateUTC = this.endDateUTC,
        liquipedia = this.liquipedia,
        qualifier = this.qualifier,
        lan = this.lan,
    )
}
