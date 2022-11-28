package com.adammcneilly.pocketleague.data.local

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.EventRegion
import com.adammcneilly.pocketleague.core.models.EventStage
import com.adammcneilly.pocketleague.core.models.EventTier
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.sqldelight.LocalEvent
import com.adammcneilly.pocketleague.sqldelight.LocalEventStage
import com.adammcneilly.pocketleague.sqldelight.LocalTeam
import com.adammcneilly.pocketleague.sqldelight.SelectById
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
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
        return database.localEventQueries
            .selectById(eventId)
            .asFlow()
            .mapToList()
            // We get one row for each event/stage combo, so we need to combine
            // them ourselves.
            .map { eventWithStageLists ->
                eventWithStageLists
                    .groupBy(SelectById::id)
                    .map { (_, queryRows) ->
                        val eventStages = queryRows.map { row ->
                            EventStage(
                                id = row.localEventStage_id,
                                name = row.localEventStage_name,
                                region = row.localEventStage_region,
                                startDateUTC = row.localEventStage_startDateUTC,
                                endDateUTC = row.localEventStage_endDateUTC,
                                liquipedia = row.localEventStage_liquipedia,
                                qualifier = row.localEventStage_qualifier,
                                lan = row.localEventStage_lan,
                            )
                        }

                        val firstRow = queryRows.first()

                        Event(
                            id = firstRow.id,
                            name = firstRow.name,
                            startDateUTC = firstRow.startDateUTC,
                            endDateUTC = firstRow.endDateUTC,
                            imageURL = firstRow.imageURL,
                            stages = eventStages,
                            tier = EventTier.valueOf(firstRow.tier),
                            mode = firstRow.mode,
                            region = EventRegion.valueOf(firstRow.region),
                            lan = firstRow.lan,
                            // NEED TO FIX
                            prize = null,
                        )
                    }
            }
            .map(List<Event>::first)
    }
}

private fun Team.toLocalTeam(): LocalTeam {
    return LocalTeam(
        id = this.id,
        name = this.name,
        imageURL = this.imageUrl,
        isFavorite = this.isFavorite,
    )
}

private fun LocalTeam.toTeam(): Team {
    return Team(
        id = this.id,
        name = this.name,
        imageUrl = this.imageURL,
        isFavorite = this.isFavorite,
    )
}

private fun LocalEvent.toEvent(): Event {
    return Event(
        id = this.id,
        name = this.name,
        startDateUTC = this.startDateUTC,
        endDateUTC = this.endDateUTC,
        imageURL = this.imageURL,
        // ARM - NEED TO FIX
        stages = emptyList(),
        tier = EventTier.valueOf(this.tier),
        mode = this.mode,
        region = EventRegion.valueOf(this.region),
        lan = this.lan,
        // ARM - NEED TO FIX
        prize = null,
    )
}

private fun Event.toLocalEvent(): LocalEvent {
    return LocalEvent(
        id = this.id,
        name = this.name,
        startDateUTC = this.startDateUTC,
        endDateUTC = this.endDateUTC,
        imageURL = this.imageURL,
        tier = this.tier.name,
        mode = this.mode,
        region = this.region.name,
        lan = this.lan,
    )
}

private fun EventStage.toLocalEventStage(
    eventId: String
): LocalEventStage {
    return LocalEventStage(
        localEventStage_id = this.id,
        localEventStage_eventId = eventId,
        localEventStage_name = this.name,
        localEventStage_region = this.region,
        localEventStage_startDateUTC = this.startDateUTC,
        localEventStage_endDateUTC = this.endDateUTC,
        localEventStage_liquipedia = this.liquipedia,
        localEventStage_qualifier = this.qualifier,
        localEventStage_lan = this.lan,
    )
}
