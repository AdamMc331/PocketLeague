package com.adammcneilly.pocketleague.feature.feed

import com.adammcneilly.pocketleague.core.data.DataState
import com.adammcneilly.pocketleague.core.displaymodels.EventSummaryDisplayModel
import com.adammcneilly.pocketleague.core.models.Match

sealed class FeedScreenEvent {

    data class RecentMatchesStateChanged(
        val dataState: DataState<List<Match>>,
    ) : FeedScreenEvent()

    data class OngoingEventsStateChanged(
        val dataState: DataState<List<EventSummaryDisplayModel>>,
    ) : FeedScreenEvent()
}
