package com.adammcneilly.pocketleague.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.shared.models.Game
import com.adammcneilly.pocketleague.shared.models.GameTeamResult

/**
 * Displays a [game] entity as a list item, just showing the teams and their results.
 */
@Composable
fun GameListItem(
    game: Game,
) {
    Column {
        Text(text = "Game X: ${game.map}")
        Row(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            GameTeamResult(result = game.blue)

            GameTeamResult(result = game.orange)
        }
    }
}

@Composable
private fun RowScope.GameTeamResult(
    result: GameTeamResult,
) {
    Column(
        modifier = Modifier
            .weight(1F),
    ) {
        Text(text = result.team.name)

        Text(text = result.goals.toString())
    }
}
