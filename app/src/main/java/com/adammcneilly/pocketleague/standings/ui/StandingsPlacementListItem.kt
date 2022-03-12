package com.adammcneilly.pocketleague.standings.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.android.design.theme.PocketLeagueTheme
import com.adammcneilly.pocketleague.core.ui.PocketLeagueImage
import com.adammcneilly.pocketleague.shared.eventoverview.StandingsPlacementDisplayModel

private const val PLACEMENT_WEIGHT = 1F
private const val TEAM_WEIGHT = 7F

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
        Box(
            modifier = Modifier
                .weight(PLACEMENT_WEIGHT),
        ) {
            Text(
                text = placement.placement,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.align(Alignment.Center),
            )
        }

        LetterCircle(placement)

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

@Composable
private fun LetterCircle(placement: StandingsPlacementDisplayModel) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
            ),
    ) {
        Text(
            text = placement.teamName.first().toString(),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            modifier = Modifier
                .align(Alignment.Center),
        )

        if (placement.teamLogo != null) {
            PocketLeagueImage(
                image = placement.teamLogo,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
                contentDescription = "Team Logo",
                contentScale = ContentScale.Crop,
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
        teamLogo = null,
    )

    PocketLeagueTheme {
        Surface {
            StandingsPlacementListItem(
                placement = displayModel,
            )
        }
    }
}
