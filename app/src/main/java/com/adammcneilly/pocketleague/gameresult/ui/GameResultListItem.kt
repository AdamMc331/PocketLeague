package com.adammcneilly.pocketleague.gameresult.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.core.ui.theme.PocketLeagueTheme

@Composable
fun GameResultListItem(
    result: GameResultDisplayModel,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(24.dp),
        ) {
            if (result.teamOneWinner) {
                WinnerCheckmark()
            }
        }

        Text(
            text = result.teamOneScore.toString(),
        )

        Text(
            text = result.stadium,
            modifier = Modifier
                .weight(1F),
            textAlign = TextAlign.Center,
        )

        Text(
            text = result.teamTwoScore.toString(),
        )

        Box(
            modifier = Modifier
                .size(24.dp),
        ) {
            if (result.teamTwoWinner) {
                WinnerCheckmark()
            }
        }
    }
}

@Composable
private fun WinnerCheckmark() {
    Icon(
        Icons.Default.Check,
        contentDescription = null,
        tint = Color.Green,
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
private fun GameResultListItemPreview() {
    val displayModel = GameResultDisplayModel(
        stadium = "Mannfield (Night)",
        teamOneScore = 1,
        teamTwoScore = 2,
    )

    PocketLeagueTheme {
        Surface {
            GameResultListItem(
                result = displayModel,
            )
        }
    }
}
