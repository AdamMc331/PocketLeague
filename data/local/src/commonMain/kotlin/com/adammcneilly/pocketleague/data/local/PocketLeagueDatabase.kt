package com.adammcneilly.pocketleague.data.local

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
     * Stores a list of [teams] inside the DB.
     */
    suspend fun storeTeams(
        teams: List<Team>,
    )
}
