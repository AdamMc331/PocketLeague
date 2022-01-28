package com.adammcneilly.pocketleague.teamlist.data

import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.core.domain.models.Team
import javax.inject.Inject

/**
 * Defines the data contract for fetching collections of teams.
 */
interface TeamListService {
    /**
     * Fetch all available RLCS teams.
     */
    suspend fun fetchAllTeams(): Result<List<Team>>
}

/**
 * This exists temporarily to keep things compiling, but screens for this aren't actually in use
 * right now.
 */
class MockTeamListService @Inject constructor() : TeamListService {
    override suspend fun fetchAllTeams(): Result<List<Team>> {
        TODO("Not yet implemented")
    }
}
