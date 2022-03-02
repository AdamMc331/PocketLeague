package com.adammcneilly.pocketleague.eventsummary.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.R
import com.adammcneilly.pocketleague.core.ui.LocalWindowSize
import com.adammcneilly.pocketleague.core.ui.UIImage
import com.adammcneilly.pocketleague.core.ui.WindowSize
import com.adammcneilly.pocketleague.core.ui.theme.PocketLeagueTheme
import com.adammcneilly.pocketleague.eventsummary.EventSummaryDisplayModel

private const val COMPACT_ITEM_WIDTH_PERCENTAGE = 1F
private const val MEDIUM_ITEM_WIDTH_PERCENTAGE = 0.75F
private const val EXPANDED_ITEM_WIDTH_PERCENTAGE = 0.5F

/**
 * Renders a scrollable list of [displayModels] for event summaries.
 */
@Composable
fun EventSummaryList(
    displayModels: List<EventSummaryDisplayModel>,
    eventClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val itemWidthPercentage = when (LocalWindowSize.current) {
        WindowSize.Compact -> COMPACT_ITEM_WIDTH_PERCENTAGE
        WindowSize.Medium -> MEDIUM_ITEM_WIDTH_PERCENTAGE
        WindowSize.Expanded -> EXPANDED_ITEM_WIDTH_PERCENTAGE
    }

    LazyColumn(
        modifier = modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(displayModels) { displayModel ->
            EventSummaryListItem(
                displayModel = displayModel,
                eventClicked = eventClicked,
                modifier = Modifier
                    .fillMaxWidth(fraction = itemWidthPercentage),
            )
        }
    }
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
private fun EventSummaryListPreview() {
    val displayModel = EventSummaryDisplayModel(
        eventId = "1234",
        startDate = "Nov 12, 2021",
        eventName = "Main Event",
        tournamentName = "RLCS 2021-22 Season - Fall Split Regional 3 - North America",
        subtitle = "16 Teams",
        image = UIImage.AndroidResource(R.drawable.us),
    )

    val displayModels = (1..10).map {
        displayModel
    }

    PocketLeagueTheme {
        Surface {
            EventSummaryList(
                displayModels = displayModels,
                eventClicked = {},
            )
        }
    }
}
