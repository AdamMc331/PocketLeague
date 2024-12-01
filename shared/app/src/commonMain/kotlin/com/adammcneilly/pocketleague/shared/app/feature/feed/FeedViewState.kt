package com.adammcneilly.pocketleague.shared.app.feature.feed

import com.adammcneilly.pocketleague.shared.app.core.displaymodels.EventGroupDisplayModel
import com.adammcneilly.pocketleague.shared.app.core.displaymodels.MatchDetailDisplayModel

/**
 * User friendly representation of the state of the [FeedScreen].
 */
data class FeedViewState(
    val recentMatches: List<MatchDetailDisplayModel>,
    val ongoingEvents: List<EventGroupDisplayModel>,
    val upcomingEvents: List<EventGroupDisplayModel>,
) {
    companion object {
        private const val PLACEHOLDER_LIST_COUNT = 3

        /**
         * Returns a default instance of [FeedViewState] where all display models are set
         * to placeholders to display during a default loading state.
         */
        fun placeholderState(): FeedViewState {
            val recentMatches = List(PLACEHOLDER_LIST_COUNT) {
                MatchDetailDisplayModel.placeholder
            }

            return FeedViewState(
                recentMatches = recentMatches,
                ongoingEvents = EventGroupDisplayModel.placeholder,
                upcomingEvents = EventGroupDisplayModel.placeholder,
            )
        }
    }
}
