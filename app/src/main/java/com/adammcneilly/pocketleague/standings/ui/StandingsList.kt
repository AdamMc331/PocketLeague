package com.adammcneilly.pocketleague.standings.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.adammcneilly.pocketleague.core.ui.Material3Divider
import com.adammcneilly.pocketleague.core.ui.theme.PocketLeagueTheme

/**
 * Shows a collection of [standings] for some event.
 */
@Composable
fun StandingsList(
    standings: List<StandingsListItemDisplayModel>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        standings.forEach { standingsListItem ->
            StandingsListItem(displayModel = standingsListItem)

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
    val displayModels = (1..10).map {
        StandingsListItemDisplayModel(
            placement = it.toString(),
            teamName = "Pittsburgh Knights",
        )
    }

    PocketLeagueTheme {
        Surface {
            StandingsList(
                standings = displayModels,
            )
        }
    }
}
