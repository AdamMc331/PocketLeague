package com.adammcneilly.pocketleague.teamlist.domain.usecases

import com.adammcneilly.pocketleague.teamlist.domain.models.FetchTeamListResult

/**
 * A use case to fetch all RLCS teams.
 */
interface FetchAllTeamsUseCase {
    /**
     * @see [FetchAllTeamsUseCase]
     */
    suspend operator fun invoke(): FetchTeamListResult
}
