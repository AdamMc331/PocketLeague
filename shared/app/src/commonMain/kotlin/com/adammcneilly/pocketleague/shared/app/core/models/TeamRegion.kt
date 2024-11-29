package com.adammcneilly.pocketleague.shared.app.core.models

/**
 * Represents a region that a team resides in.
 */
enum class TeamRegion(
    val liquipediaRegionKey: String,
) {
    NA(liquipediaRegionKey = "North America"),
    EU(liquipediaRegionKey = "Europe"),
    OCE(liquipediaRegionKey = "Oceania"),
    SAM(liquipediaRegionKey = "South America"),
    APAC(liquipediaRegionKey = "Asia-Pacific"),
    MENA(liquipediaRegionKey = "Middle East and North Africa"),
    SSA(liquipediaRegionKey = "Sub-Saharan Africa"),
    INT(liquipediaRegionKey = "N/A"),
    Unknown(liquipediaRegionKey = "N/A"),
}
