package com.adammcneilly.pocketleague.core.models

/**
 * Represents a region that an event takes place in.
 */
enum class EventRegion(
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
