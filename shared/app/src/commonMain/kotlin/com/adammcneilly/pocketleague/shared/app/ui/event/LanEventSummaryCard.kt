package com.adammcneilly.pocketleague.shared.app.ui.event

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.adammcneilly.pocketleague.shared.app.core.displaymodels.EventSummaryDisplayModel
import com.adammcneilly.pocketleague.shared.app.core.displaymodels.TeamOverviewDisplayModel
import com.adammcneilly.pocketleague.shared.app.ui.components.InlineIconText
import com.adammcneilly.pocketleague.shared.app.ui.components.VerticalSpacer
import com.adammcneilly.pocketleague.shared.app.ui.theme.PocketLeagueTheme
import com.adammcneilly.pocketleague.shared.app.ui.theme.md_theme_dark_onSurface
import com.adammcneilly.pocketleague.shared.app.ui.theme.rlcsBlue
import com.adammcneilly.pocketleague.shared.app.ui.theme.rlcsOrange

/**
 * A custom card component used to highlight a LAN event. Unlike an online
 * regional, a LAN event is an in-person international competition and deserves
 * special highlighting within [com.adammcneilly.pocketleague.shared.app.feature.feed.FeedContent].
 */
@Composable
fun LanEventSummaryCard(
    event: EventSummaryDisplayModel,
    onEventClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val rlcsGradientBrush = Brush.horizontalGradient(
        colors = listOf(rlcsBlue, rlcsOrange),
    )

    CompositionLocalProvider(
        LocalContentColor provides md_theme_dark_onSurface,
    ) {
        Box(
            modifier = modifier
                .background(
                    brush = rlcsGradientBrush,
                    shape = MaterialTheme.shapes.medium,
                )
                .fillMaxWidth()
                .clickable {
                    onEventClicked.invoke(event.eventId)
                },
        ) {
            Column(
                modifier = Modifier
                    .padding(PocketLeagueTheme.sizes.cardPadding),
            ) {
                EventName(event.name)

                VerticalSpacer(PocketLeagueTheme.sizes.cardPadding)

                // If we have a winning team, it implies
                // the event is over, so we'll render it slightly
                // differently
                val winningTeam = event.winningTeam

                if (winningTeam != null) {
                    EventWinner(winningTeam)
                } else {
                    EventDates(event.dateRange)

                    EventLocation(event.arenaLocation)
                }
            }
        }
    }
}

@Composable
private fun EventWinner(
    winningTeam: TeamOverviewDisplayModel,
) {
    InlineIconText(
        text = winningTeam.name,
        icon = Icons.Default.EmojiEvents,
        leadingIcon = true,
        modifier = Modifier
            .fillMaxWidth(),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.headlineSmall,
    )
}

@Composable
private fun EventLocation(
    location: String,
) {
    Text(
        text = location,
        style = MaterialTheme.typography.labelMedium,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth(),
    )
}

@Composable
private fun EventDates(
    dateRange: String,
) {
    Text(
        text = dateRange,
        style = MaterialTheme.typography.labelMedium,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth(),
    )
}

@Composable
private fun EventName(
    name: String,
) {
    Text(
        text = name,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth(),
    )
}
