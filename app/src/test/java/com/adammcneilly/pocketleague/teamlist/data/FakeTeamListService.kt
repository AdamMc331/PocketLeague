package com.adammcneilly.pocketleague.teamlist.data

import com.adammcneilly.pocketleague.shared.core.models.Team
import com.adammcneilly.pocketleague.shared.data.Result

class FakeTeamListService : TeamListService {
    private lateinit var mockAllTeamsResult: Result<List<Team>>

    fun mockAllTeamsResult(
        result: Result<List<Team>>,
    ) {
        mockAllTeamsResult = result
    }

    override suspend fun fetchAllTeams(): Result<List<Team>> {
        return mockAllTeamsResult
    }
}
