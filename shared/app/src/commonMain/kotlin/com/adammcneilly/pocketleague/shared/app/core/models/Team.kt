package com.adammcneilly.pocketleague.shared.app.core.models

/**
 * Represents a team that either actively or has in the past participated in a Rocket
 * League event.
 */
data class Team(
    val id: String,
    val name: String,
    val isFavorite: Boolean,
    val isActive: Boolean,
    val region: Region,
    val lightThemeImageURL: String?,
    val darkThemeImageURL: String? = lightThemeImageURL,
)
