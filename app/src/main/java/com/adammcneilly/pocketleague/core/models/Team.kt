package com.adammcneilly.pocketleague.core.models

data class Team(
    val name: String,
    val logoImageUrl: String,
    val roster: List<Player>,
)
