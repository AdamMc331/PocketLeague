package com.adammcneilly.pocketleague.eventsummary.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.R
import com.adammcneilly.pocketleague.core.ui.UIImage
import com.adammcneilly.pocketleague.core.ui.theme.PocketLeagueTheme

/**
 * Renders a scrollable list of [displayModels] for event summaries.
 */
@Composable
fun EventSummaryList(
    displayModels: List<EventSummaryDisplayModel>,
    eventClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(displayModels) { displayModel ->
            EventSummaryListItem(
                displayModel = displayModel,
                eventClicked = eventClicked,
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
        image = UIImage.Resource(R.drawable.us),
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
