package com.adammcneilly.pocketleague.core.domain.models

data class Team(
    val name: String,
    val lightThemeLogoImageUrl: String,
    val darkThemeLogoImageUrl: String,
    val roster: List<Player>,
)
