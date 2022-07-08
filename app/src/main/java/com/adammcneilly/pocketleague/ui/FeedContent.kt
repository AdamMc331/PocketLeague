package com.adammcneilly.pocketleague.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.R
import com.adammcneilly.pocketleague.composables.eventsummary.EventSummaryListItem
import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.shared.screens.feed.FeedViewState
import com.adammcneilly.pocketleague.ui.components.EmptyStateCard

private const val MATCH_CARD_WIDTH_RATIO = 0.8F

/**
 * Shows content inside the feed screen for the given [viewState].
 */
@Composable
fun FeedContent(
    viewState: FeedViewState,
    modifier: Modifier = Modifier,
    onMatchClicked: (Match) -> Unit = {},
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
    onMatchClicked: (Match) -> Unit,
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
                RecentMatchesRow(viewState, onMatchClicked)
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
            ongoingEventsList(viewState, onEventClicked)
        } else {
            item {
                OngoingEventsEmptyState()
            }
        }
    }
}

private fun LazyListScope.ongoingEventsList(
    viewState: FeedViewState,
    onEventClicked: (String) -> Unit,
) {
    itemsIndexed(viewState.ongoingEvents) { index, event ->
        EventSummaryListItem(
            displayModel = event,
            modifier = Modifier
                .clickable {
                    onEventClicked.invoke(event.eventId)
                }
        )

        if (index != viewState.ongoingEvents.lastIndex) {
            Divider()
        }
    }
}

@Composable
private fun RecentMatchesEmptyState() {
    EmptyStateCard(
        text = stringResource(id = R.string.err_no_recent_matches),
        modifier = Modifier
            .padding(
                horizontal = 16.dp,
            ),
        textModifier = Modifier
            .padding(32.dp),
    )
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

@Composable
private fun RecentMatchesRow(
    viewState: FeedViewState,
    onMatchClicked: (Match) -> Unit,
) {
    LazyRow(
        contentPadding = PaddingValues(
            horizontal = 16.dp,
        ),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(viewState.recentMatches) { match ->
            RecentMatchCard(
                match = match,
                modifier = Modifier
                    .fillParentMaxWidth(MATCH_CARD_WIDTH_RATIO)
                    .clickable {
                        onMatchClicked.invoke(match)
                    },
            )
        }
    }
}
