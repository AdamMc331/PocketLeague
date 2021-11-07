package com.adammcneilly.pocketleague.swiss.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.R
import com.adammcneilly.pocketleague.core.ui.Material3VerticalDivider
import com.adammcneilly.pocketleague.core.ui.UIImage
import com.adammcneilly.pocketleague.core.ui.theme.PocketLeagueTheme
import com.adammcneilly.pocketleague.core.ui.theme.WinnerBackgroundColor
import com.adammcneilly.pocketleague.seriesoverview.ui.SeriesOverviewDisplayModel
import com.adammcneilly.pocketleague.teamoverview.ui.TeamOverviewDisplayModel

@Composable
fun SwissSeriesOverviewItem(
    seriesOverview: SeriesOverviewDisplayModel,
    modifier: Modifier = Modifier,
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12F),
            )
    ) {
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Min)
        ) {
            FirstTeamResult(
                teamName = seriesOverview.teamOne.name,
                teamWins = seriesOverview.teamOneWins,
                isWinner = seriesOverview.teamOneWinner,
                modifier = Modifier.weight(1F),
            )

            Material3VerticalDivider(
                color = MaterialTheme.colorScheme.onBackground,
            )

            SecondTeamResult(
                teamName = seriesOverview.teamTwo.name,
                teamWins = seriesOverview.teamTwoWins,
                isWinner = seriesOverview.teamTwoWinner,
                modifier = Modifier.weight(1F),
            )
        }
    }
}

@Composable
private fun FirstTeamResult(
    teamName: String,
    teamWins: Int,
    isWinner: Boolean,
    modifier: Modifier = Modifier,
) {
    val fontWeight = if (isWinner) {
        FontWeight.Bold
    } else {
        FontWeight.Normal
    }

    val backgroundColor = if (isWinner) {
        MaterialTheme.colorScheme.surface
    } else {
        WinnerBackgroundColor
    }

    Row(
        modifier = modifier
            .height(IntrinsicSize.Max),
    ) {
        Text(
            text = teamName,
            fontWeight = fontWeight,
            modifier = Modifier
                .background(
                    color = backgroundColor,
                )
                .weight(1F)
                .padding(8.dp),
            textAlign = TextAlign.Center,
        )

        Material3VerticalDivider(
            color = MaterialTheme.colorScheme.onBackground,
        )

        Text(
            text = teamWins.toString(),
            fontWeight = fontWeight,
            modifier = Modifier
                .padding(8.dp),
        )
    }
}

@Composable
private fun SecondTeamResult(
    teamName: String,
    teamWins: Int,
    isWinner: Boolean,
    modifier: Modifier = Modifier,
) {
    val fontWeight = if (isWinner) {
        FontWeight.Bold
    } else {
        FontWeight.Normal
    }

    val backgroundColor = if (isWinner) {
        MaterialTheme.colorScheme.surface
    } else {
        Color.Green
    }

    Row(
        modifier = modifier
            .height(IntrinsicSize.Max),
    ) {
        Text(
            text = teamWins.toString(),
            fontWeight = fontWeight,
            modifier = Modifier
                .padding(8.dp),
        )

        Material3VerticalDivider(
            color = MaterialTheme.colorScheme.onBackground,
        )

        Text(
            text = teamName,
            fontWeight = fontWeight,
            modifier = Modifier
                .background(
                    color = backgroundColor,
                )
                .weight(1F)
                .padding(8.dp),
            textAlign = TextAlign.Center,
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
private fun SwissSeriesOverviewItemPreview() {
    val overview = SeriesOverviewDisplayModel(
        teamOne = TeamOverviewDisplayModel(
            name = "G2",
            logoImage = UIImage.Resource(R.drawable.us),
        ),
        teamTwo = TeamOverviewDisplayModel(
            name = "GG",
            logoImage = UIImage.Resource(R.drawable.us),
        ),
        teamOneWins = 4,
        teamTwoWins = 2,
    )

    PocketLeagueTheme {
        SwissSeriesOverviewItem(
            seriesOverview = overview,
        )
    }
}
