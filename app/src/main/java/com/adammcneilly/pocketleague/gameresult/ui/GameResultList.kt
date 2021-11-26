package com.adammcneilly.pocketleague.gameresult.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.adammcneilly.pocketleague.core.ui.Material3Divider
import com.adammcneilly.pocketleague.core.ui.theme.PocketLeagueTheme

/**
 * Shows a list of [games] between two teams. Could be used for a bracket series, swiss series, etc.
 */
@Composable
fun GameResultList(
    games: List<GameResultDisplayModel>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        games.forEach { gameResult ->
            GameResultListItem(
                result = gameResult,
            )

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
private fun GameResultListPreview() {
    val gameOne = GameResultDisplayModel(
        stadium = "Mannfield (Night)",
        teamOneScore = 1,
        teamTwoScore = 2,
    )

    val gameTwo = GameResultDisplayModel(
        stadium = "Forbidden Temple",
        teamOneScore = 2,
        teamTwoScore = 1,
    )

    val gameThree = GameResultDisplayModel(
        stadium = "DFH Stadium",
        teamOneScore = 1,
        teamTwoScore = 0,
    )

    val gameFour = GameResultDisplayModel(
        stadium = "Utopia Coliseum (Dusk)",
        teamOneScore = 2,
        teamTwoScore = 3,
    )

    val gameFive = GameResultDisplayModel(
        stadium = "Champions Field",
        teamOneScore = 2,
        teamTwoScore = 3,
    )

    val games = listOf(
        gameOne,
        gameTwo,
        gameThree,
        gameFour,
        gameFive,
    )

    PocketLeagueTheme {
        Surface {
            GameResultList(
                games = games,
            )
        }
    }
}
