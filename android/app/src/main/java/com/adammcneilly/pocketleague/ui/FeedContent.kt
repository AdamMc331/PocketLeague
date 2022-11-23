package com.adammcneilly.pocketleague.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.R
import com.adammcneilly.pocketleague.android.designsystem.components.EmptyStateCard
import com.adammcneilly.pocketleague.android.designsystem.matches.MatchesCarousel
import com.adammcneilly.pocketleague.android.designsystem.matches.RecentMatchesEmptyState
import com.adammcneilly.pocketleague.core.displaymodels.EventSummaryDisplayModel
import com.adammcneilly.pocketleague.shared.screens.feed.FeedViewState
import com.adammcneilly.pocketleague.ui.composables.eventsummary.EventSummaryListItem

/**
 * Shows content inside the feed screen for the given [viewState].
 */
@Composable
fun FeedContent(
    viewState: FeedViewState,
    modifier: Modifier = Modifier,
    onMatchClicked: (String) -> Unit = {},
    onEventClicked: (String) -> Unit = {},
) {
    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        SuccessContent(
            viewState = viewState,
            onMatchClicked = onMatchClicked,
            onEventClicked = onEventClicked,
        )
    }
}

@Composable
private fun SuccessContent(
    viewState: FeedViewState,
    onMatchClicked: (String) -> Unit,
    onEventClicked: (String) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {

        item {
            Text(
                text = "Recent Matches",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .padding(16.dp),
            )
        }

        item {
            if (viewState.recentMatches.isNotEmpty()) {
                MatchesCarousel(viewState.recentMatches, onMatchClicked)
            } else {
                RecentMatchesEmptyState()
            }
        }

        item {
            Text(
                text = "Ongoing Events",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .padding(16.dp),
            )
        }

        if (viewState.ongoingEvents.isNotEmpty()) {
            eventList(viewState.ongoingEvents, onEventClicked)
        } else {
            item {
                OngoingEventsEmptyState()
            }
        }

        item {
            Text(
                text = "Upcoming Events",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .padding(16.dp),
            )
        }

        if (viewState.upcomingEvents.isNotEmpty()) {
            eventList(viewState.upcomingEvents, onEventClicked)
        } else {
            item {
                OngoingEventsEmptyState()
            }
        }
    }
}

private fun LazyListScope.eventList(
    events: List<EventSummaryDisplayModel>,
    onEventClicked: (String) -> Unit,
) {
    itemsIndexed(events) { index, event ->
        EventSummaryListItem(
            displayModel = event,
            modifier = Modifier
                .clickable {
                    onEventClicked.invoke(event.eventId)
                }
        )

        if (index != events.lastIndex) {
            Divider()
        }
    }
}

@Composable
private fun OngoingEventsEmptyState() {
    EmptyStateCard(
        text = stringResource(id = R.string.err_no_ongoing_events),
        modifier = Modifier
            .padding(
                horizontal = 16.dp,
            ),
        textModifier = Modifier
            .padding(32.dp),
    )
}
