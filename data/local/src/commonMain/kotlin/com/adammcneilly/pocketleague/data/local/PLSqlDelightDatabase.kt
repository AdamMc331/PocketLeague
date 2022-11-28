package com.adammcneilly.pocketleague.data.local

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.EventRegion
import com.adammcneilly.pocketleague.core.models.EventTier
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.sqldelight.LocalEvent
import com.adammcneilly.pocketleague.sqldelight.LocalTeam
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOne
import kotlinx.coroutines.flow.Flow
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
            }
        }
    }

    override fun getEvent(eventId: String): Flow<Event> {
        return database.localEventQueries
            .selectById(eventId)
            .asFlow()
            .mapToOne()
            .map(LocalEvent::toEvent)
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
