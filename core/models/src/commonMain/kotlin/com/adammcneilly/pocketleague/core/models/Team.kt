package com.adammcneilly.pocketleague.core.models

/**
 * Represents a team that either actively or has in the past participated in a Rocket
 * League event.
 */
data class Team(
    val id: String = "",
    val name: String = "TBD",
    val lightThemeImageURL: String? = null,
    val darkThemeImageURL: String? = lightThemeImageURL,
    val isFavorite: Boolean = false,
    val isActive: Boolean = false,
    // We either should rename the EventRegion class, or consider a separate enum for `TeamRegion`.
    val region: EventRegion = EventRegion.Unknown,
)
