package com.adammcneilly.pocketleague.shared.standings

import com.adammcneilly.pocketleague.shared.core.ui.UIImage

/**
 * User friendly information to display a list item in a collection of standings.
 */
data class StandingsPlacementDisplayModel(
    val placement: String,
    val teamName: String,
    val roster: String,
    val teamLogo: UIImage?,
)
