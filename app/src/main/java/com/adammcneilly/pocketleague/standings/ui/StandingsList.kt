package com.adammcneilly.pocketleague.standings.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.adammcneilly.pocketleague.core.ui.Material3Divider
import com.adammcneilly.pocketleague.android.design.theme.PocketLeagueTheme

/**
 * Shows a collection of [standings] for some event.
 */
@Composable
fun StandingsList(
    standings: StandingsDisplayModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        standings.placements.forEach { placement ->
            StandingsPlacementListItem(placement = placement)

            Material3Divider()
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
private fun StandingsListPreview() {
    val placements = (1..10).map {
        StandingsPlacementDisplayModel(
            placement = it.toString(),
            teamName = "Pittsburgh Knights",
            roster = "sosa / AlphaKep / ElOmarMaton",
            teamLogo = null,
        )
    }

    val standings = StandingsDisplayModel(
        placements = placements,
    )

    PocketLeagueTheme {
        Surface {
            StandingsList(
                standings = standings,
            )
        }
    }
}
