package com.adammcneilly.pocketleague.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
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
import com.adammcneilly.pocketleague.android.designsystem.matches.RecentMatchesEmptyState
import com.adammcneilly.pocketleague.core.displaymodels.EventSummaryDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.feature.feed.FeedViewState
import com.adammcneilly.pocketleague.shared.ui.match.MatchCarousel
import com.adammcneilly.pocketleague.ui.composables.eventsummary.EventSummaryListItem

/**
 * Shows content inside the feed screen for the given [viewState].
 */
@Composable
fun FeedContent(
    viewState: FeedViewState,
    modifier: Modifier = Modifier,
    onMatchClicked: (Match.Id) -> Unit = {},
    onEventClicked: (Event.Id) -> Unit = {},
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
    onMatchClicked: (Match.Id) -> Unit,
    onEventClicked: (Event.Id) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        item {
            FeedSectionTitle(title = "Recent Matches")
        }

        item {
            RecentMatchesSection(
                viewState.recentMatches,
                onMatchClicked,
            )
        }

        item {
            FeedSectionTitle(title = "Ongoing Events")
        }

        if (viewState.ongoingEvents.isNotEmpty()) {
            eventList(viewState.ongoingEvents, onEventClicked)
        } else {
            item {
                EventListEmptyState(
                    textRes = R.string.err_no_ongoing_events,
                )
            }
        }

        item {
            FeedSectionTitle(title = "Upcoming Events")
        }

        if (viewState.upcomingEvents.isNotEmpty()) {
            eventList(viewState.upcomingEvents, onEventClicked)
        } else {
            item {
                EventListEmptyState(
                    textRes = R.string.err_no_upcoming_events,
                )
            }
        }
    }
}

@Composable
private fun FeedSectionTitle(
    title: String,
) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier
            .padding(16.dp),
    )
}

@Composable
private fun RecentMatchesSection(
    recentMatches: List<MatchDetailDisplayModel>,
    onMatchClicked: (Match.Id) -> Unit,
) {
    if (recentMatches.isNotEmpty()) {
        MatchCarousel(
            matches = recentMatches,
            onMatchClicked = onMatchClicked,
            contentPadding = PaddingValues(16.dp),
        )
    } else {
        RecentMatchesEmptyState()
    }
}

private fun LazyListScope.eventList(
    events: List<EventSummaryDisplayModel>,
    onEventClicked: (Event.Id) -> Unit,
) {
    itemsIndexed(events) { index, event ->
        EventSummaryListItem(
            displayModel = event,
            modifier = Modifier
                .clickable {
                    onEventClicked.invoke(event.eventId)
                },
        )

        if (index != events.lastIndex) {
            Divider()
        }
    }
}

@Composable
private fun EventListEmptyState(
    @StringRes textRes: Int,
) {
    EmptyStateCard(
        text = stringResource(id = textRes),
        modifier = Modifier
            .padding(
                horizontal = 16.dp,
            ),
        textModifier = Modifier
            .padding(32.dp),
    )
}
