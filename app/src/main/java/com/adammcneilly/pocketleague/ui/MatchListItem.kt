package com.adammcneilly.pocketleague.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchTeamResultDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel
import com.adammcneilly.pocketleague.ui.theme.PocketLeagueTheme

/**
 * Renders a [displayModel] inside of an individual list item along side other matches
 * in a vertical scrolling manner.
 */
@Composable
fun MatchListItem(
    displayModel: MatchDetailDisplayModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp),
    ) {
        MatchTeamResultRow(displayModel = displayModel.blueTeamResult)

        MatchTeamResultRow(displayModel = displayModel.orangeTeamResult)
    }
}

@Composable
private fun MatchTeamResultRow(
    displayModel: MatchTeamResultDisplayModel,
) {
    val fontWeight: FontWeight? = if (displayModel.winner) {
        FontWeight.Bold
    } else {
        null
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = displayModel.score,
            fontWeight = fontWeight,
        )

        InlineIconText(
            text = displayModel.team.name,
            icon = Icons.Default.EmojiEvents,
            showIcon = displayModel.winner,
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
private fun MatchListItemPreview() {
    val match = MatchDetailDisplayModel(
        blueTeamResult = MatchTeamResultDisplayModel(
            team = TeamOverviewDisplayModel(
                name = "G2 Esports",
            ),
            winner = true,
            score = "4",
        ),
        orangeTeamResult = MatchTeamResultDisplayModel(
            team = TeamOverviewDisplayModel(
                name = "FaZe Clan",
            ),
            winner = false,
            score = "0",
        ),
    )

    PocketLeagueTheme {
        Surface {
            MatchListItem(displayModel = match)
        }
    }
}
