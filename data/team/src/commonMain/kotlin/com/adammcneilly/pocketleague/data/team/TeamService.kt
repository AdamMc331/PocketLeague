package com.adammcneilly.pocketleague.data.team

import com.adammcneilly.pocketleague.core.models.DataState
import com.adammcneilly.pocketleague.core.models.Team

/**
 * Defines the data contract for any requests related to teams within
 * RLCS.
 */
interface TeamService {

    /**
     * Return a list of [Team] entities that are favorited by the user.
     */
    suspend fun getFavoriteTeams(): DataState<List<Team>>

    /**
     * Return a list of [Team] entities for all of the active teams in RLCS.
     */
    suspend fun getActiveRLCSTeams(): DataState<List<Team>>
}
