package com.adammcneilly.pocketleague.core.models

/**
 * Represents a team that either actively or has in the past participated in a Rocket
 * League event.
 */
data class Team(
    val id: String = "",
    val name: String = "TBD",
    val imageUrl: String? = null,
    val isFavorite: Boolean = false,
    val isActive: Boolean? = null
)
