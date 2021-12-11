package com.adammcneilly.pocketleague.teamlist.data

import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.core.domain.models.Team

/**
 * Defines the data contract for fetching collections of teams.
 */
interface TeamListService {
    /**
     * Fetch all available RLCS teams.
     */
    suspend fun fetchAllTeams(): Result<List<Team>>
}
