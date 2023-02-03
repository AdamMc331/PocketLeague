package com.adammcneilly.pocketleague.data.team

import com.adammcneilly.pocketleague.core.models.Team
import kotlinx.coroutines.flow.Flow

/**
 * Defines the data contract for any requests related to teams within
 * RLCS.
 */
interface TeamRepository {

    /**
     * Return a list of [Team] entities that are favorited by the user.
     */
    fun getFavoriteTeams(): Flow<List<Team>>

    /**
     * Return a list of [Team] entities for all of the active teams in RLCS.
     */
    fun getActiveRLCSTeams(): Flow<List<Team>>

    /**
     * Persist the supplied [teams] in the data source.
     */
    suspend fun insertTeams(teams: List<Team>)

    /**
     * Update the [teamId] to whether or not it [isFavorite] in our data source.
     */
    suspend fun updateIsFavorite(
        teamId: String,
        isFavorite: Boolean
    )
}
