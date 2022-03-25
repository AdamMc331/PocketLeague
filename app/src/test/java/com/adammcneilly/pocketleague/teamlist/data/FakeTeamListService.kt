package com.adammcneilly.pocketleague.teamlist.data

import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.shared.core.models.Team

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
