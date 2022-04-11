package com.adammcneilly.pocketleague.team.api

import com.adammcneilly.pocketleague.core.data.DataResult
import com.adammcneilly.pocketleague.core.models.Team
import kotlinx.coroutines.flow.Flow

/**
 * An interface defining the data contract for all requests within the
 * team domain.
 */
interface TeamRepository {

    /**
     * Requests a list of [Team] entities for all of the active teams within RLCS.
     */
    fun fetchActiveTeams(): Flow<DataResult<List<Team>>>
}
