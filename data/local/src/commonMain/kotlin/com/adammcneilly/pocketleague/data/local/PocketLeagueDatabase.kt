package com.adammcneilly.pocketleague.data.local

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.Team
import kotlinx.coroutines.flow.Flow

/**
 * Defines the contract for all requests that will be made for a local PocketLeague database.
 */
interface PocketLeagueDatabase {

    /**
     * Retrieve all the [Team] entities from our DB that are favorited by the user.
     */
    fun getFavoriteTeams(): Flow<List<Team>>

    /**
     * Retrieve all of the [Team] entities stored in our DB.
     */
    fun getAllTeams(): Flow<List<Team>>

    /**
     * Stores a list of [teams] inside the DB.
     */
    suspend fun storeTeams(
        teams: List<Team>,
    )

    /**
     * Retrieve a list of [Event] entities that are upcoming (haven't started yet).
     */
    fun getUpcomingEvents(): Flow<List<Event>>

    /**
     * Retrieve a list of [Event] entities that are happening now.
     */
    fun getOngoingEvents(): Flow<List<Event>>

    /**
     * Stores a list of [events] inside our local DB.
     */
    suspend fun storeEvents(
        events: List<Event>,
    )

    /**
     * Retrieve an event by unique identifier.
     */
    fun getEvent(eventId: String): Flow<Event>
}
