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
    recentMatches: List<MatchDetailDisplayModel>,
    ongoingEvents: List<EventGroupDisplayModel>,
    upcomingEvents: List<EventGroupDisplayModel>,
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
                matches = recentMatches,
                contentPadding = PaddingValues(
                    horizontal = PocketLeagueTheme.sizes.screenPadding,
                ),
            ) {}
        }

        item {
            FeedSectionHeader(
                text = "Happening Now",
                modifier = Modifier
                    .feedHorizontalPadding(),
            )
        }

        ongoingEvents.forEach { group ->
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

        item {
            FeedSectionHeader(
                text = "Upcoming",
                modifier = Modifier
                    .feedHorizontalPadding(),
            )
        }

        upcomingEvents.forEach { group ->
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
