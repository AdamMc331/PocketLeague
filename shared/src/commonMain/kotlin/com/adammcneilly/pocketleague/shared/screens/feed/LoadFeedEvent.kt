package com.adammcneilly.pocketleague.shared.screens.feed

import com.adammcneilly.pocketleague.core.data.DataState
import com.adammcneilly.pocketleague.core.displaymodels.toDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.toSummaryDisplayModel
import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.data.event.EventListRequest
import com.adammcneilly.pocketleague.data.match.MatchListRequest
import com.adammcneilly.pocketleague.shared.screens.Events
import kotlinx.datetime.Clock
import kotlin.time.Duration.Companion.days

const val NUM_DAYS_RECENT_MATCHES = 7

/**
 * Loads the information for the feed state.
 */
fun Events.loadFeed() = screenCoroutine {
    val ongoingEventsRequest = EventListRequest(
        date = Clock.System.now(),
        group = "rlcs",
    )

    val repoResult = repository.eventService.fetchEvents(
        ongoingEventsRequest,
    )

    stateManager.updateScreen(FeedViewState::class) {
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

        it.copy(
            ongoingEventsState = mappedResult,
        )
    }

    val recentMatchesRequest = MatchListRequest(
        before = Clock.System.now(),
        after = Clock.System.now().minus(NUM_DAYS_RECENT_MATCHES.days),
        group = "rlcs",
    )

    val matchListResult = repository.matchService.fetchMatches(
        request = recentMatchesRequest,
    )

    val mappedResult = matchListResult.map { matches ->
        matches.map(Match::toDetailDisplayModel)
    }

    stateManager.updateScreen(FeedViewState::class) {
        it.copy(
            recentMatchesState = mappedResult,
        )
    }
}
