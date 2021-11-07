package com.adammcneilly.pocketleague.teamlist.domain.usecases

import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.teamlist.data.TeamListService
import com.adammcneilly.pocketleague.teamlist.domain.models.FetchTeamListResult

class FetchAllTeamsUseCaseImpl(
    private val service: TeamListService,
) : FetchAllTeamsUseCase {

    override suspend fun invoke(): FetchTeamListResult {
        val result = service.fetchAllTeams()

        return when (result) {
            is Result.Success -> {
                FetchTeamListResult.Success(result.data)
            }
            is Result.Error -> {
                FetchTeamListResult.Failure
            }
        }
    }
}
