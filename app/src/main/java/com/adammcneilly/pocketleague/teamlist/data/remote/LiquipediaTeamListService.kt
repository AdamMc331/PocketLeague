package com.adammcneilly.pocketleague.teamlist.data.remote

import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.core.data.remote.liquipedia.LiquipediaRetrofitAPI
import com.adammcneilly.pocketleague.core.domain.models.Team
import com.adammcneilly.pocketleague.teamlist.data.TeamListService
import javax.inject.Inject

class LiquipediaTeamListService @Inject constructor(
    private val api: LiquipediaRetrofitAPI,
) : TeamListService {

    override suspend fun fetchAllTeams(): Result<List<Team>> {
        val liquipediaResponse = api.fetchTeamsPage()

        val body = liquipediaResponse?.body()?.parse?.text?.x

        println(body)

        return Result.Success(emptyList())
    }
}
