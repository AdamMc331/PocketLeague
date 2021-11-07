package com.adammcneilly.pocketleague.teamlist.domain.models

import com.adammcneilly.pocketleague.core.models.Team

sealed class FetchTeamListResult {
    data class Success(val teams: List<Team>) : FetchTeamListResult()

    object Failure : FetchTeamListResult()
}
