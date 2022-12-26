package com.adammcneilly.pocketleague.feature.feed

import com.adammcneilly.pocketleague.core.displaymodels.EventSummaryDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.feature.ScreenState

/**
 * Defines the UI configuration for the [com.adammcneilly.pocketleague.shared.screens.Screens.Feed] screen.
 */
data class FeedViewState(
    val ongoingEvents: List<EventSummaryDisplayModel> = List(3) {
        EventSummaryDisplayModel.placeholder
    },
    val recentMatches: List<MatchDetailDisplayModel> = List(3) {
        MatchDetailDisplayModel.placeholder
    },
    val upcomingEvents: List<EventSummaryDisplayModel> = List(3) {
        EventSummaryDisplayModel.placeholder
    },
) : ScreenState {

    override val title: String? = null
}
