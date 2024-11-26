package com.adammcneilly.pocketleague.shared.app.feed

import com.adammcneilly.pocketleague.core.displaymodels.EventGroupDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel

data class FeedUiState(
    val recentMatches: List<MatchDetailDisplayModel>,
    val ongoingEvents: List<EventGroupDisplayModel>,
    val upcomingEvents: List<EventGroupDisplayModel>,
) {
    companion object {
        private const val PLACEHOLDER_LIST_COUNT = 3

        /**
         * Returns a default instance of [FeedUiState] where all display models are set
         * to placeholders to display during a default loading state.
         */
        fun placeholderState(): FeedUiState {
            val recentMatches = List(PLACEHOLDER_LIST_COUNT) {
                MatchDetailDisplayModel.placeholder
            }

            return FeedUiState(
                recentMatches = recentMatches,
                ongoingEvents = EventGroupDisplayModel.placeholder,
                upcomingEvents = EventGroupDisplayModel.placeholder,
            )
        }
    }
}
