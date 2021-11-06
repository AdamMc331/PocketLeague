package com.adammcneilly.pocketleague.core.models

import java.time.LocalDate

data class Player(
    val country: String,
    val gamerTag: String,
    val realName: String,
    val joinDate: LocalDate,
    val notes: String? = null,
)
