package com.adammcneilly.pocketleague.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.core.displaymodels.GameDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.GameTeamResultDisplayModel
import com.adammcneilly.pocketleague.ui.theme.PocketLeagueTheme

/**
 * Renders the [displayModel] to show information about a game between
 * two teams.
 */
@Composable
fun GameListItem(
    displayModel: GameDetailDisplayModel,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {

        Text(
            text = displayModel.blueTeamResult.score,
        )

        Text(
            text = displayModel.map,
        )

        Text(
            text = displayModel.orangeTeamResult.score,
        )
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
private fun GameListItemPreview() {
    val game = GameDetailDisplayModel(
        orangeTeamResult = GameTeamResultDisplayModel(
            score = "1",
            winner = true,
        ),
        blueTeamResult = GameTeamResultDisplayModel(
            score = "0",
        ),
        map = "DFH Stadium",
    )

    PocketLeagueTheme {
        Surface {
            GameListItem(displayModel = game)
        }
    }
}
