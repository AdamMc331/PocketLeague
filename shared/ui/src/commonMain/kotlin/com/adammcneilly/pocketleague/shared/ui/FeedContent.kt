package com.adammcneilly.pocketleague.shared.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.shared.design.system.theme.PocketLeagueTheme

/**
 * The main list of events and matches to show within the feed screen
 * that is the landing page when opening the app.
 */
@Composable
fun FeedContent(
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(PocketLeagueTheme.sizes.screenPadding),
        verticalArrangement = Arrangement.spacedBy(PocketLeagueTheme.sizes.listItemSpacing),
    ) {
        item {
            Text(
                text = "Upcoming Events",
                style = MaterialTheme.typography.headlineSmall,
            )
        }

        item {
            EventSummaryListCard()
        }

        item {
            LanEventCard()
        }
    }
}
