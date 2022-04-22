package com.adammcneilly.pocketleague.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.shared.models.Game
import com.google.accompanist.placeholder.material.placeholder

/**
 * Displays a [game] entity as a list item, just showing the teams and their results.
 */
@Composable
fun GameListItem(
    game: Game,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = game.blue.goals.toString(),
            modifier = Modifier
                .placeholder(
                    visible = game.blue.goals == -1,
                    shape = CircleShape,
                ),
        )

        Text(
            text = "Game ${game.number}\n${game.map}",
            modifier = Modifier
                .weight(1F)
                .padding(horizontal = 16.dp)
                .placeholder(
                    visible = game.map.isEmpty(),
                    shape = CircleShape,
                ),
            textAlign = TextAlign.Center,
        )

        Text(
            text = game.orange.goals.toString(),
            modifier = Modifier
                .placeholder(
                    visible = game.orange.goals == -1,
                    shape = CircleShape,
                ),
        )
    }
}
