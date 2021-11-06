package com.adammcneilly.pocketleague.seriesresult.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.R
import com.adammcneilly.pocketleague.core.ui.Material3Divider
import com.adammcneilly.pocketleague.core.ui.UIImage
import com.adammcneilly.pocketleague.core.ui.theme.PocketLeagueTheme
import com.adammcneilly.pocketleague.gameresult.ui.GameResultDisplayModel
import com.adammcneilly.pocketleague.gameresult.ui.GameResultList
import com.adammcneilly.pocketleague.teamdetail.ui.TeamDetailDisplayModel

@Composable
fun SeriesResultContent(
    result: SeriesResultDisplayModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            Text(
                text = result.teamOne.name,
                style = MaterialTheme.typography.headlineSmall,
            )

            Text(
                text = result.teamTwo.name,
                style = MaterialTheme.typography.headlineSmall,
            )
        }

        Material3Divider()

        Text(
            text = result.gameTime,
            modifier = Modifier
                .padding(8.dp),
        )

        Material3Divider()

        GameResultList(games = result.games)
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
private fun SeriesResultContentPreview() {
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

    val teamOne = TeamDetailDisplayModel(
        name = "NRG",
        logo = UIImage.Resource(R.drawable.us),
        players = emptyList(),
    )

    val teamTwo = TeamDetailDisplayModel(
        name = "NV",
        logo = UIImage.Resource(R.drawable.ca),
        players = emptyList(),
    )

    val result = SeriesResultDisplayModel(
        gameTime = "October 29, 2021 - 18:30",
        games = games,
        teamOne = teamOne,
        teamTwo = teamTwo,
    )

    PocketLeagueTheme {
        Surface {
            SeriesResultContent(result = result)
        }
    }
}
