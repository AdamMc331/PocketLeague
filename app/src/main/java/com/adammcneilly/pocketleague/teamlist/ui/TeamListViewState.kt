package com.adammcneilly.pocketleague.teamlist.ui

import com.adammcneilly.pocketleague.teamoverview.ui.TeamOverviewDisplayModel

/**
 * All information required to present a list of teams, including the loading content and error states.
 */
data class TeamListViewState(
    val showLoading: Boolean = true,
    val showContent: Boolean = false,
    val showError: Boolean = false,
    val teams: List<TeamOverviewDisplayModel> = emptyList(),
)
