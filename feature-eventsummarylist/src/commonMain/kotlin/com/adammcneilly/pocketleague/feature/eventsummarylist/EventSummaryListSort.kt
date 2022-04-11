package com.adammcneilly.pocketleague.feature.eventsummarylist

/**
 * Possible sort variations for a list of event summaries.
 *
 * @property[displayText] The user friendly explanation of this text to show to the user.
 */
enum class EventSummaryListSort(
    val displayText: String,
) {
    UPCOMING(
        displayText = "Upcoming",
    ),
    PAST(
        displayText = "Past",
    ),
}
