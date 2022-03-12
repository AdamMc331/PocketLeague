package com.adammcneilly.pocketleague.shared.eventoverview

import com.adammcneilly.pocketleague.core.ui.UIImage

/**
 * User friendly information to display a list item in a collection of standings.
 */
data class StandingsPlacementDisplayModel(
    val placement: String,
    val teamName: String,
    val roster: String,
    val teamLogo: UIImage?,
)
