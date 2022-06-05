package com.adammcneilly.pocketleague.shared.screens.feed

import com.adammcneilly.pocketleague.core.data.DataState
import com.adammcneilly.pocketleague.core.data.models.EventListRequest
import com.adammcneilly.pocketleague.core.data.models.MatchListRequest
import com.adammcneilly.pocketleague.core.displaymodels.toSummaryDisplayModel
import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.shared.screens.Events
import kotlinx.coroutines.flow.collect
import kotlinx.datetime.Clock
import kotlin.time.Duration.Companion.days

const val NUM_DAYS_RECENT_MATCHES = 3
/**
 * Loads the information for the feed state.
 */
fun Events.loadFeed() = screenCoroutine {
    val ongoingEventsRequest = EventListRequest(
        date = Clock.System.now(),
//        group = "rlcs",
    )

    repository.eventRepository.fetchEvents(
        ongoingEventsRequest,
    ).collect { repoResult ->
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
    }

    val recentMatchesRequest = MatchListRequest(
        before = Clock.System.now(),
        after = Clock.System.now().minus(NUM_DAYS_RECENT_MATCHES.days),
//        group = "rlcs",
//        region = "NA",
    )

    repository.matchRepository.fetchMatches(
        request = recentMatchesRequest,
    ).collect { repoResult ->
        stateManager.updateScreen(FeedViewState::class) {
            it.copy(
                recentMatchesState = repoResult,
            )
        }
    }
}
