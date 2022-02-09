package com.adammcneilly.pocketleague.models

/**
 * The domain information regarding any RLCS player.
 */
data class Player(
    val countryCode: String,
    val gamerTag: String,
    val realName: String,
    val notes: String? = null,
)
