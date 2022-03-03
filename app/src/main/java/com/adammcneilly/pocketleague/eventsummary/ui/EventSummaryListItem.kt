package com.adammcneilly.pocketleague.eventsummary.ui

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.R
import com.adammcneilly.pocketleague.core.ui.Material3Card
import com.adammcneilly.pocketleague.core.ui.UIImage
import com.adammcneilly.pocketleague.android.design.theme.PocketLeagueTheme
import com.adammcneilly.pocketleague.eventsummary.EventSummaryDisplayModel

/**
 * Renders a [displayModel] to show a summary of an RLCS event.
 */
@Composable
fun EventSummaryListItem(
    displayModel: EventSummaryDisplayModel,
    eventClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Material3Card(
        modifier = modifier
            .clickable(
                onClick = {
                    eventClicked.invoke(displayModel.eventId)
                },
            ),
    ) {
        Column {
            // Removing this for the time being, I don't think it was as good as I expected.
            // But I think I could benefit from this code in the future.
//            PocketLeagueImage(
//                image = displayModel.image,
//                contentDescription = stringResource(R.string.event_image_content_description),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .aspectRatio(1F),
//                contentScale = ContentScale.Crop,
//            )

            SummaryInfo(displayModel)
        }
    }
}

@Composable
private fun SummaryInfo(
    displayModel: EventSummaryDisplayModel
) {
    Column(
        modifier = Modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        EventDateLabel(eventDate = displayModel.startDate)

        TournamentNameLabel(tournamentName = displayModel.tournamentName)

        EventNameLabel(eventName = displayModel.eventName)

        val subtitle = displayModel.subtitle

        if (subtitle != null) {
            EventSubtitleLabel(eventSubtitle = subtitle)
        }
    }
}

@Composable
private fun EventSubtitleLabel(
    eventSubtitle: String,
) {
    Text(
        text = eventSubtitle,
        style = MaterialTheme.typography.bodySmall,
    )
}

@Composable
private fun EventDateLabel(
    eventDate: String,
) {
    Text(
        text = eventDate.toUpperCase(Locale.current),
        style = MaterialTheme.typography.labelSmall,
    )
}

@Composable
private fun TournamentNameLabel(
    tournamentName: String,
) {
    Text(
        text = tournamentName,
        style = MaterialTheme.typography.headlineSmall,
    )
}

@Composable
private fun EventNameLabel(
    eventName: String
) {
    Text(
        text = eventName,
        style = MaterialTheme.typography.bodyMedium,
    )
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
private fun EventSummaryListItemPreview() {
    val displayModel = EventSummaryDisplayModel(
        eventId = "1234",
        startDate = "Nov 12, 2021",
        eventName = "Main Event",
        tournamentName = "RLCS 2021-22 Season - Fall Split Regional 3 - North America",
        subtitle = "16 Teams",
        image = UIImage.AndroidResource(R.drawable.us),
    )

    PocketLeagueTheme {
        Surface {
            EventSummaryListItem(
                displayModel = displayModel,
                eventClicked = {},
                modifier = Modifier
                    .padding(16.dp),
            )
        }
    }
}
