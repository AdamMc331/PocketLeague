package com.adammcneilly.pocketleague.shared.ui.feed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import com.adammcneilly.pocketleague.core.displaymodels.EventGroupDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.shared.design.system.theme.PocketLeagueTheme
import com.adammcneilly.pocketleague.shared.ui.event.EventSummaryListCard
import com.adammcneilly.pocketleague.shared.ui.event.LanEventSummaryCard
import com.adammcneilly.pocketleague.shared.ui.match.MatchCarousel

/**
 * The main list of events and matches to show within the feed screen
 * that is the landing page when opening the app.
 */
@Composable
fun FeedContent(
    matches: List<MatchDetailDisplayModel>,
    eventGroups: List<EventGroupDisplayModel>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(
            vertical = PocketLeagueTheme.sizes.screenPadding,
        ),
        verticalArrangement = Arrangement.spacedBy(PocketLeagueTheme.sizes.listItemSpacing),
    ) {
        item {
            FeedSectionHeader(
                text = "Recent Matches",
                modifier = Modifier
                    .feedHorizontalPadding(),
            )
        }

        item {
            MatchCarousel(
                matches = matches,
                contentPadding = PaddingValues(
                    horizontal = PocketLeagueTheme.sizes.screenPadding,
                ),
            ) {}
        }

        item {
            FeedSectionHeader(
                text = "Upcoming Events",
                modifier = Modifier
                    .feedHorizontalPadding(),
            )
        }

        eventGroups.forEach { group ->
            when (group) {
                is EventGroupDisplayModel.Regionals -> {
                    item {
                        EventSummaryListCard(
                            events = group.events,
                            modifier = Modifier
                                .feedHorizontalPadding(),
                        )
                    }
                }

                is EventGroupDisplayModel.Major -> {
                    item {
                        LanEventSummaryCard(
                            event = group.event,
                            modifier = Modifier
                                .feedHorizontalPadding(),
                        )
                    }
                }
            }
        }
    }
}

private fun Modifier.feedHorizontalPadding() = composed {
    this.padding(horizontal = PocketLeagueTheme.sizes.screenPadding)
}
