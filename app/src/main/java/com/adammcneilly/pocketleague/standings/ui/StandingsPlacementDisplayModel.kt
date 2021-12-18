package com.adammcneilly.pocketleague.standings.ui

/**
 * User friendly information to display a list item in a collection of standings.
 */
data class StandingsPlacementDisplayModel(
    val placement: String,
    val teamName: String,
    val roster: String,
)
