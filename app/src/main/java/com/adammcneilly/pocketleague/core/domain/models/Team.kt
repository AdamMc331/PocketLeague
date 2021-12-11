package com.adammcneilly.pocketleague.core.domain.models

/**
 * A [Team] is any collection of players that competes in RLCS.
 */
data class Team(
    val name: String,
    val lightThemeLogoImageUrl: String,
    val darkThemeLogoImageUrl: String,
    val roster: List<Player>,
)
