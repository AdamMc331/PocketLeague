package com.adammcneilly.pocketleague.core.domain.models

data class Player(
    val countryCode: String,
    val gamerTag: String,
    val realName: String,
    val notes: String? = null,
)
