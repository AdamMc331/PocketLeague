package com.adammcneilly.pocketleague.player.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.ui.theme.PocketLeagueTheme

@Composable
fun PlayerListItem(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
    ) {
        Text(
            text = "McLando",
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
@Preview(
    name = "Dynamic Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    apiLevel = 31,
)
@Preview(
    name = "Dynamic Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    apiLevel = 31,
)
@Composable
private fun PlayerListItemPreview() {
    PocketLeagueTheme {
        Surface {
            PlayerListItem(
                modifier = Modifier
                    .padding(16.dp),
            )
        }
    }
}
