package com.adammcneilly.pocketleague.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.core.displaymodels.PlayerGameStatsDisplayModel
import com.adammcneilly.pocketleague.ui.theme.PocketLeagueTheme

/**
 * Renders a [displayModel] to show the stats of a player's performance
 * within an individual game.
 */
@Composable
fun PlayerGameStatsRow(
    displayModel: PlayerGameStatsDisplayModel,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 8.dp,
                vertical = 4.dp,
            ),
    ) {
        Text(
            text = displayModel.playerName,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .weight(2F),
        )

        StatCell(
            text = displayModel.score,
        )

        StatCell(
            text = displayModel.goals,
        )

        StatCell(
            text = displayModel.assists,
        )

        StatCell(
            text = displayModel.saves,
        )

        StatCell(
            text = displayModel.shots,
        )
    }
}

@Composable
private fun RowScope.StatCell(
    text: String,
) {
    Text(
        text = text,
        textAlign = TextAlign.End,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
            .weight(1F),
    )
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
private fun PlayerGameStatsRowPreview() {
    PocketLeagueTheme {
        Surface {
            PlayerGameStatsRow(
                displayModel = PlayerGameStatsDisplayModel(
                    playerName = "trk511",
                    score = "342",
                    goals = "1",
                    assists = "0",
                    saves = "1",
                    shots = "5",
                )
            )
        }
    }
}
