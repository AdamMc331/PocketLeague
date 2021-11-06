package com.adammcneilly.pocketleague.core.models

data class Player(
    val countryCode: String,
    val gamerTag: String,
    val realName: String,
    val notes: String? = null,
)
