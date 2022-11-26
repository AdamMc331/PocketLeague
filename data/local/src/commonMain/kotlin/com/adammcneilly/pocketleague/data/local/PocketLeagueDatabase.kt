package com.adammcneilly.pocketleague.data.local

import com.adammcneilly.pocketleague.core.models.Team
import kotlinx.coroutines.flow.Flow

/**
 * Defines the contract for all requests that will be made for a local PocketLeague database.
 */
interface PocketLeagueDatabase {

    /**
     *
     */
    fun getFavoriteTeams(): Flow<List<Team>>
}
