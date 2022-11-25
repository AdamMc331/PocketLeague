package com.adammcneilly.pocketleague.shared.screens.feed

import com.adammcneilly.pocketleague.core.data.DataState
import com.adammcneilly.pocketleague.core.displaymodels.EventSummaryDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.shared.screens.ScreenState

/**
 * Defines the UI configuration for the [com.adammcneilly.pocketleague.shared.screens.Screens.Feed] screen.
 */
data class FeedViewState(
    val ongoingEventsState: DataState<List<EventSummaryDisplayModel>> = DataState.Loading,
    val recentMatchesState: DataState<List<MatchDetailDisplayModel>> = DataState.Loading,
    val upcomingEventsState: DataState<List<EventSummaryDisplayModel>> = DataState.Loading,
) : ScreenState {

    override val title: String? = null

    val ongoingEvents: List<EventSummaryDisplayModel>
        get() = when (ongoingEventsState) {
            is DataState.Loading -> {
                // Here, we return a list of empty Event objects which will be mapped
                // to a placeholder loading UI.
                (1..3).map {
                    EventSummaryDisplayModel.placeholder
                }
            }
            is DataState.Success -> {
                ongoingEventsState.data
            }
            is DataState.Error -> {
                emptyList()
            }
        }

    val upcomingEvents: List<EventSummaryDisplayModel>
        get() = when (upcomingEventsState) {
            is DataState.Loading -> {
                // Here, we return a list of empty Event objects which will be mapped
                // to a placeholder loading UI.
                (1..3).map {
                    EventSummaryDisplayModel.placeholder
                }
            }
            is DataState.Success -> {
                upcomingEventsState.data
            }
            is DataState.Error -> {
                emptyList()
            }
        }

    val recentMatches: List<MatchDetailDisplayModel>
        get() = when (recentMatchesState) {
            is DataState.Loading -> {
                (1..3).map {
                    MatchDetailDisplayModel.placeholder
                }
            }
            is DataState.Success -> {
                recentMatchesState.data
            }
            is DataState.Error -> {
                emptyList()
            }
        }
}
