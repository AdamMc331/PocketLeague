package com.adammcneilly.pocketleague.standings.ui

import com.adammcneilly.pocketleague.teamoverview.ui.TeamOverviewDisplayModel

/**
 * User friendly information to display a list item in a collection of standings.
 */
data class StandingsPlacementDisplayModel(
    val placement: String,
    val team: TeamOverviewDisplayModel
)
