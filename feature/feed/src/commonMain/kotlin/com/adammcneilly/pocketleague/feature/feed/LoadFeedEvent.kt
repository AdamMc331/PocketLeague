package com.adammcneilly.pocketleague.feature.feed

import com.adammcneilly.pocketleague.core.data.DataState
import com.adammcneilly.pocketleague.core.displaymodels.toSummaryDisplayModel
import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.data.event.EventListRequest
import com.adammcneilly.pocketleague.data.event.EventService
import com.adammcneilly.pocketleague.data.match.MatchListRequest
import com.adammcneilly.pocketleague.data.match.MatchService

suspend fun loadFeed(
    eventService: EventService,
    matchService: MatchService,
    eventProcessor: (FeedScreenEvent) -> Unit,
) {
    loadOngoingEvents(eventService, eventProcessor)
    loadRecentMatches(matchService, eventProcessor)
}

private suspend fun loadRecentMatches(
    matchService: MatchService,
    eventProcessor: (FeedScreenEvent) -> Unit,
) {
    val recentMatchesRequest = MatchListRequest(
        group = "rlcs",
    )

    val matchListResult = matchService.fetchMatches(
        request = recentMatchesRequest,
    )

    val event = FeedScreenEvent.RecentMatchesStateChanged(
        matchListResult,
    )

    eventProcessor.invoke(event)
}

private suspend fun loadOngoingEvents(
    eventService: EventService,
    eventProcessor: (FeedScreenEvent) -> Unit,
) {
    val ongoingEventsRequest = EventListRequest(
        group = "rlcs",
    )

    val repoResult = eventService.fetchEvents(
        ongoingEventsRequest,
    )

    val mappedResult = when (repoResult) {
        is DataState.Loading -> {
            DataState.Loading
        }
        is DataState.Success -> {
            DataState.Success(
                data = repoResult.data.map(Event::toSummaryDisplayModel)
            )
        }
        is DataState.Error -> {
            DataState.Error(repoResult.error)
        }
    }

    val event = FeedScreenEvent.OngoingEventsStateChanged(
        mappedResult
    )

    eventProcessor.invoke(event)
}
