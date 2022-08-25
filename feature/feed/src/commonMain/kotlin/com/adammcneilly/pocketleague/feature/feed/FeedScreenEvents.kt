package com.adammcneilly.pocketleague.feature.feed

import com.adammcneilly.pocketleague.core.data.DataState
import com.adammcneilly.pocketleague.core.displaymodels.EventSummaryDisplayModel
import com.adammcneilly.pocketleague.core.models.Match

sealed class FeedScreenEvents {

    data class RecentMatchesStateChanged(
        val dataState: DataState<List<Match>>,
    ) : FeedScreenEvents()

    data class OngoingEventsStateChanged(
        val dataState: DataState<List<EventSummaryDisplayModel>>,
    ) : FeedScreenEvents()
}
