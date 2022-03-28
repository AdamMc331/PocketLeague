package com.adammcneilly.pocketleague.shared.eventsummarylist

import com.adammcneilly.pocketleague.shared.core.ui.UIText

/**
 * Possible sort variations for a list of event summaries.
 *
 * @property[displayText] The user friendly explanation of this text to show to the user.
 */
enum class EventSummaryListSort(
    val displayText: UIText,
) {
    UPCOMING(
        displayText = UIText.StringText("Upcoming")
    ),
    PAST(
        displayText = UIText.StringText("Past")
    ),
}
