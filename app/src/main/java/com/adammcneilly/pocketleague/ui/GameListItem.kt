package com.adammcneilly.pocketleague.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleDown
import androidx.compose.material.icons.filled.ArrowCircleUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    val showDetailedStats = remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .clickable {
                showDetailedStats.value = !showDetailedStats.value
            }
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
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

        val iconToUse = if (showDetailedStats.value) {
            Icons.Default.ArrowCircleUp
        } else {
            Icons.Default.ArrowCircleDown
        }

        val contentDescriptionToUse = if (showDetailedStats.value) {
            "Click To Expand Game Details"
        } else {
            "Click To Collapse Game Details"
        }

        Icon(
            imageVector = iconToUse,
            contentDescription = contentDescriptionToUse,
            modifier = Modifier
                .padding(top = 16.dp),
        )

        AnimatedVisibility(visible = showDetailedStats.value) {
            CoreStatsComparison(
                blueTeamStats = game.blue.teamStats.core,
                orangeTeamStats = game.orange.teamStats.core,
                modifier = Modifier
                    .padding(16.dp),
            )
        }
    }
}
