package com.adammcneilly.pocketleague.teamlist.domain.usecases

import com.adammcneilly.pocketleague.teamlist.domain.models.FetchTeamListResult

interface FetchAllTeamsUseCase {
    suspend operator fun invoke(): FetchTeamListResult
}
