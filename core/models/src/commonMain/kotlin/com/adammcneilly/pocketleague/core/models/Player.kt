package com.adammcneilly.pocketleague.core.models

/**
 * Represents a person who can compete in an RLCS event.
 */
data class Player(
    val id: String = "",
    val slug: String = "",
    val tag: String = "",
    val country: String = ""
)
