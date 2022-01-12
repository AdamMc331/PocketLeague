package com.adammcneilly.pocketleague.eventsummary.ui

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.R
import com.adammcneilly.pocketleague.core.ui.Material3Card
import com.adammcneilly.pocketleague.core.ui.PocketLeagueImage
import com.adammcneilly.pocketleague.core.ui.UIImage
import com.adammcneilly.pocketleague.core.ui.theme.PocketLeagueTheme

/**
 * Renders a [displayModel] to show a summary of an RLCS event.
 */
@Composable
fun EventSummaryListItem(
    displayModel: EventSummaryDisplayModel,
    modifier: Modifier = Modifier,
) {
    Material3Card(
        modifier = modifier
            .clickable(onClick = displayModel.onClick),
    ) {
        Column {
            PocketLeagueImage(
                image = displayModel.image,
                contentDescription = stringResource(R.string.event_image_content_description),
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1F),
                contentScale = ContentScale.Crop,
            )

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

        if (displayModel.subtitle != null) {
            EventSubtitleLabel(eventSubtitle = displayModel.subtitle)
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
        startDate = "Nov 12, 2021",
        eventName = "Main Event",
        tournamentName = "RLCS 2021-22 Season - Fall Split Regional 3 - North America",
        subtitle = "16 Teams",
        onClick = {},
        image = UIImage.Resource(R.drawable.us),
    )

    PocketLeagueTheme {
        Surface {
            EventSummaryListItem(
                displayModel = displayModel,
                modifier = Modifier
                    .padding(16.dp),
            )
        }
    }
}
