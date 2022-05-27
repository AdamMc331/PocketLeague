package com.adammcneilly.pocketleague.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.adammcneilly.pocketleague.core.displaymodels.CoreStatsDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.GamePlayerResultDisplayModel
import com.adammcneilly.pocketleague.ui.theme.PocketLeagueTheme

/**
 * Shows a list of players and their stats within a game. Likely to be used
 * to show how a team performed.
 */
@Composable
fun PlayerStatsTable(
    displayModels: List<GamePlayerResultDisplayModel>,
    showFinalDivider: Boolean = false,
) {
    Column {
        StatTableRow(
            title = "Player",
            cells = listOf(
                "Score",
                "Goals",
                "Assists",
                "Saves",
                "Shots",
            ),
            boldCells = true,
        )

        Divider()

        displayModels.forEachIndexed { index, displayModel ->
            StatTableRow(
                title = displayModel.playerName,
                cells = listOf(
                    displayModel.coreStats.score,
                    displayModel.coreStats.goals,
                    displayModel.coreStats.assists,
                    displayModel.coreStats.saves,
                    displayModel.coreStats.shots,
                ),
            )

            if (index != displayModels.lastIndex || showFinalDivider) {
                Divider()
            }
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
private fun PlayerStatsTablePreview() {
    val trk = GamePlayerResultDisplayModel(
        playerName = "trk511",
        coreStats = CoreStatsDisplayModel(
            score = "342",
            goals = "1",
            assists = "0",
            saves = "1",
            shots = "5",
        ),
    )

    val jstn = GamePlayerResultDisplayModel(
        playerName = "jstn",
        coreStats = CoreStatsDisplayModel(
            score = "1,042",
            goals = "10",
            assists = "0",
            saves = "1",
            shots = "10",
        ),
    )

    PocketLeagueTheme {
        Surface {
            PlayerStatsTable(
                displayModels = listOf(trk, jstn),
            )
        }
    }
}
