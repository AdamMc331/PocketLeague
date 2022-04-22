package com.adammcneilly.pocketleague.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.shared.models.Match
import com.adammcneilly.pocketleague.shared.screens.feed.FeedViewState

private const val MATCH_CARD_WIDTH_RATIO = 0.8F

/**
 * Shows content inside the feed screen for the given [viewState].
 */
@Composable
fun FeedContent(
    viewState: FeedViewState,
    modifier: Modifier = Modifier,
    onMatchClicked: (Match) -> Unit = {},
) {
    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        SuccessContent(
            viewState = viewState,
            onMatchClicked = onMatchClicked,
        )
    }
}

@Composable
private fun SuccessContent(
    viewState: FeedViewState,
    onMatchClicked: (Match) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {

        item {
            Text(
                text = "Recent Matches",
                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .padding(16.dp),
            )
        }

        item {
            LazyRow(
                contentPadding = PaddingValues(
                    horizontal = 16.dp,
                ),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(viewState.recentMatches) { match ->
                    MatchListItem(
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

        item {
            Text(
                text = "Upcoming Events",
                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .padding(16.dp),
            )
        }

        itemsIndexed(viewState.upcomingEvents) { index, event ->
            EventSummaryListItem(displayModel = event)

            if (index != viewState.upcomingEvents.lastIndex) {
                Divider()
            }
        }
    }
}
