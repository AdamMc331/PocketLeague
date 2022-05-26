package com.adammcneilly.pocketleague.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.adammcneilly.pocketleague.core.displaymodels.PlayerGameStatsDisplayModel
import com.adammcneilly.pocketleague.ui.theme.PocketLeagueTheme

/**
 * Shows a list of players and their stats within a game. Likely to be used
 * to show how a team performed.
 */
@Composable
fun PlayerGameStatsList(
    displayModels: List<PlayerGameStatsDisplayModel>,
) {
    Column {
        displayModels.forEach { displayModel ->
            PlayerGameStatsRow(displayModel = displayModel)

            Divider()
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
@Suppress("UnusedPrivateMember")
private fun PlayerGameStatsListPreview() {
    val trk = PlayerGameStatsDisplayModel(
        playerName = "trk511",
        score = "342",
        goals = "1",
        assists = "0",
        saves = "1",
        shots = "5",
    )

    val jstn = PlayerGameStatsDisplayModel(
        playerName = "jstn",
        score = "1,042",
        goals = "10",
        assists = "0",
        saves = "1",
        shots = "10",
    )

    PocketLeagueTheme {
        Surface {
            PlayerGameStatsList(
                displayModels = listOf(trk, jstn),
            )
        }
    }
}
