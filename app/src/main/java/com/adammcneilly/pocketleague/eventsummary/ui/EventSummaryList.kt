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
import com.adammcneilly.pocketleague.ExcludeFromJacocoGeneratedReport
import com.adammcneilly.pocketleague.R
import com.adammcneilly.pocketleague.android.design.adaptiveWidth
import com.adammcneilly.pocketleague.android.design.theme.PocketLeagueTheme
import com.adammcneilly.pocketleague.shared.core.ui.UIImage
import com.adammcneilly.pocketleague.shared.eventsummarylist.EventSummaryListItemDisplayModel

/**
 * Renders a scrollable list of [displayModels] for event summaries.
 */
@Composable
fun EventSummaryList(
    displayModels: List<EventSummaryListItemDisplayModel>,
    eventClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .adaptiveWidth(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(displayModels) { displayModel ->
            EventSummaryListItem(
                displayModel = displayModel,
                eventClicked = eventClicked,
                modifier = Modifier
                    .fillMaxWidth(),
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
@Preview(
    name = "Medium",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    widthDp = 800,
)
@Preview(
    name = "Expanded",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    widthDp = 900,
)
@Composable
@ExcludeFromJacocoGeneratedReport
private fun EventSummaryListPreview() {
    val displayModel = EventSummaryListItemDisplayModel(
        eventId = "1234",
        startDate = "Nov 12, 2021",
        eventName = "Main Event",
        tournamentName = "RLCS 2021-22 Season - Fall Split Regional 3 - North America",
        subtitle = "16 Teams",
        image = UIImage.AndroidResource(R.drawable.us),
    )

    val displayModels = (1..2).map {
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
