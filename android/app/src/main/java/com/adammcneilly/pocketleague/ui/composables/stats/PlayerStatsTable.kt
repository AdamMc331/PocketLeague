package com.adammcneilly.pocketleague.ui.composables.stats

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.adammcneilly.pocketleague.core.displaymodels.GamePlayerResultDisplayModel

/**
 * Shows a list of players and their stats within a game. Likely to be used
 * to show how a team performed.
 */
@Composable
fun PlayerStatsTable(
    displayModels: List<GamePlayerResultDisplayModel>,
    showFinalDivider: Boolean = false
) {
    Column {
        StatTableRow(
            title = "Player",
            cells = listOf(
                "Score",
                "Goals",
                "Assists",
                "Saves",
                "Shots"
            ),
            boldCells = true,
            textStyle = MaterialTheme.typography.bodyMedium
        )

        Divider()

        displayModels.forEachIndexed { index, displayModel ->
            StatTableRow(
                title = displayModel.player.tag,
                cells = listOf(
                    displayModel.coreStats.score.toString(),
                    displayModel.coreStats.goals.toString(),
                    displayModel.coreStats.assists.toString(),
                    displayModel.coreStats.saves.toString(),
                    displayModel.coreStats.shots.toString()
                )
            )

            if (index != displayModels.lastIndex || showFinalDivider) {
                Divider()
            }
        }
    }
}
