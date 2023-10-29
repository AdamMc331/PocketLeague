package com.adammcneilly.pocketleague.shared.ui.feed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.core.displaymodels.EventGroupDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.shared.ui.match.MatchCarousel
import com.adammcneilly.pocketleague.shared.ui.theme.PocketLeagueTheme
import com.adammcneilly.pocketleague.shared.ui.utils.screenHorizontalPadding

/**
 * The main list of events and matches to show within the feed screen
 * that is the landing page when opening the app.
 */
@Composable
fun FeedContent(
    recentMatches: List<MatchDetailDisplayModel>,
    ongoingEvents: List<EventGroupDisplayModel>,
    upcomingEvents: List<EventGroupDisplayModel>,
    onMatchClicked: (Match.Id) -> Unit,
    onEventClicked: (Event.Id) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding =
            PaddingValues(
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
    onEventClicked: (Event.Id) -> Unit,
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
            contentPadding =
                PaddingValues(
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
        modifier =
            modifier
                .screenHorizontalPadding(),
    )
}
