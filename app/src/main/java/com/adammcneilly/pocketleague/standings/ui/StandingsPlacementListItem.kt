package com.adammcneilly.pocketleague.standings.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.core.ui.theme.PocketLeagueTheme

private const val PLACEMENT_WEIGHT = 1F
private const val TEAM_WEIGHT = 6F

/**
 * Given a [placement], render information about a team's standing within some event.
 */
@Composable
fun StandingsPlacementListItem(
    placement: StandingsPlacementDisplayModel,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = placement.placement,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .weight(PLACEMENT_WEIGHT)
        )

        Column(
            modifier = Modifier
                .weight(TEAM_WEIGHT),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = placement.teamName,
                fontWeight = FontWeight.Bold,
            )

            Text(
                text = placement.roster,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
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
private fun StandingsListItemPreview() {
    val displayModel = StandingsPlacementDisplayModel(
        placement = "1",
        teamName = "Pittsburgh Knights",
        roster = "sosa / AlphaKep / ElOmarMaton",
    )

    PocketLeagueTheme {
        Surface {
            StandingsPlacementListItem(
                placement = displayModel,
            )
        }
    }
}
