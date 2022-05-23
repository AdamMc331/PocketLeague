package com.adammcneilly.pocketleague.shared.screens.feed

import com.adammcneilly.pocketleague.core.data.DataState
import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.shared.displaymodels.EventSummaryDisplayModel
import com.adammcneilly.pocketleague.shared.screens.ScreenState

/**
 * Defines the UI configuration for the [com.adammcneilly.pocketleague.shared.screens.Screens.Feed] screen.
 */
data class FeedViewState(
    val upcomingEventsState: DataState<List<EventSummaryDisplayModel>> = DataState.Loading,
    val recentMatchesState: DataState<List<Match>> = DataState.Loading,
) : ScreenState {

    val upcomingEvents: List<EventSummaryDisplayModel>
        get() = when (upcomingEventsState) {
            is DataState.Loading -> {
                // Here, we return a list of empty Event objects which will be mapped
                // to a placeholder loading UI.
                (1..3).map {
                    EventSummaryDisplayModel()
                }
            }
            is DataState.Success -> {
                upcomingEventsState.data
            }
            is DataState.Error -> {
                emptyList()
            }
        }

    val recentMatches: List<Match>
        get() = when (recentMatchesState) {
            is DataState.Loading -> {
                // Here, we return a list of empty match objects which will be mapped
                // to a placeholder loading UI.
                listOf(Match(), Match(), Match())
            }
            is DataState.Success -> {
                recentMatchesState.data
            }
            is DataState.Error -> {
                emptyList()
            }
        }
}
