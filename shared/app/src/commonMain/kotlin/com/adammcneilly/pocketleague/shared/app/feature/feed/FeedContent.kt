package com.adammcneilly.pocketleague.shared.app.feature.feed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.shared.app.core.displaymodels.EventGroupDisplayModel
import com.adammcneilly.pocketleague.shared.app.core.displaymodels.MatchDetailDisplayModel

/**
 * The main list of events and matches to show within the feed screen
 * that is the landing page when opening the app.
 */
@Composable
fun FeedContent(
    recentMatches: List<MatchDetailDisplayModel>,
    ongoingEvents: List<EventGroupDisplayModel>,
    upcomingEvents: List<EventGroupDisplayModel>,
    onMatchClicked: (String) -> Unit,
    onEventClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(
            vertical = PocketLeagueTheme.sizes.screenPadding,
        ),
        verticalArrangement = Arrangement.spacedBy(PocketLeagueTheme.sizes.listItemSpacing),
    ) {
        recentMatchesHeader()

        recentMatchesCarousel(recentMatches, onMatchClicked)

        happeningNowHeader()

        eventGroupList(ongoingEvents, onEventClicked)

        upcomingHeader()

        eventGroupList(upcomingEvents, onEventClicked)
    }
}

private fun LazyListScope.upcomingHeader() {
    item {
        FeedSectionHeader(
            text = "Upcoming",
        )
    }
}

private fun LazyListScope.eventGroupList(
    events: List<EventGroupDisplayModel>,
    onEventClicked: (String) -> Unit,
) {
    events.forEach { group ->
        item {
            FeedEventGroup(
                group,
                onEventClicked,
            )
        }
    }
}

private fun LazyListScope.happeningNowHeader() {
    item {
        FeedSectionHeader(
            text = "Happening Now",
        )
    }
}

private fun LazyListScope.recentMatchesCarousel(
    recentMatches: List<MatchDetailDisplayModel>,
    onMatchClicked: (Match.Id) -> Unit,
) {
    item {
        MatchCarousel(
            matches = recentMatches,
            contentPadding = PaddingValues(
                horizontal = PocketLeagueTheme.sizes.screenPadding,
            ),
            onMatchClicked = onMatchClicked,
        )
    }
}

private fun LazyListScope.recentMatchesHeader() {
    item {
        FeedSectionHeader(
            text = "Recent Matches",
        )
    }
}

@Composable
private fun FeedSectionHeader(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineSmall,
        modifier = modifier
            .screenHorizontalPadding(),
    )
}

/**
 * For a given [displayModel], determine how to render
 * that collection for our [FeedContent].
 */
@Composable
private fun FeedEventGroup(
    displayModel: EventGroupDisplayModel,
    onEventClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val groupModifier = modifier
        .screenHorizontalPadding()

    when (displayModel) {
        is EventGroupDisplayModel.Regionals -> {
            EventSummaryListCard(
                events = displayModel.events,
                onEventClicked = onEventClicked,
                modifier = groupModifier,
            )
        }

        is EventGroupDisplayModel.Major -> {
            LanEventSummaryCard(
                event = displayModel.event,
                onEventClicked = onEventClicked,
                modifier = groupModifier,
            )
        }
    }
}
