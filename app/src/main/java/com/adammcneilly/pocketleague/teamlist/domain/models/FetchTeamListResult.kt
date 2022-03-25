package com.adammcneilly.pocketleague.teamlist.domain.models

import com.adammcneilly.pocketleague.shared.core.models.Team

/**
 * A collection of possible result types for fetching a list of teams.
 */
sealed class FetchTeamListResult {
    /**
     * This indicates we had a successful request to fetch the [teams].
     */
    data class Success(val teams: List<Team>) : FetchTeamListResult()

    /**
     * This indicates some error occurred when trying to request teams.
     */
    object Failure : FetchTeamListResult()
}
