package com.adammcneilly.pocketleague.shared.models

/**
 * Represents a team that either actively or has in the past participated in a Rocket
 * League event.
 */
data class Team(
    val id: String = "",
    val name: String = "",
    val imageUrl: String? = null,
)
