package com.adammcneilly.pocketleague.core.models

/**
 * Defines a location that an in person event would take place at.
 */
data class Location(
    val venue: String,
    val city: String,
    val countryCode: String,
)
