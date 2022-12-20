package com.adammcneilly.pocketleague.shared.screens.feed

import com.adammcneilly.pocketleague.core.displaymodels.EventSummaryDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.shared.screens.ScreenState

/**
 * Defines the UI configuration for the [com.adammcneilly.pocketleague.shared.screens.Screens.Feed] screen.
 */
data class FeedViewState(
    val ongoingEvents: List<EventSummaryDisplayModel> = emptyList(),
    val recentMatches: List<MatchDetailDisplayModel> = emptyList(),
    val upcomingEvents: List<EventSummaryDisplayModel> = emptyList(),
) : ScreenState {

    override val title: String? = null
}
