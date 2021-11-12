package com.adammcneilly.pocketleague.teamlist.data

import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.core.domain.models.Team

interface TeamListService {
    suspend fun fetchAllTeams(): Result<List<Team>>
}
