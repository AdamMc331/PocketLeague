package com.adammcneilly.pocketleague.player.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.core.ui.PocketLeagueImage
import com.adammcneilly.pocketleague.core.ui.UIImage
import com.adammcneilly.pocketleague.core.ui.theme.PocketLeagueTheme
import com.murgupluoglu.flagkit.FlagKit

/**
 * Given a [player], render the UI to show information about that player
 * within a list.
 */
@Composable
fun PlayerListItem(
    player: PlayerDisplayModel,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        PocketLeagueImage(
            image = player.flagImage,
            contentDescription = "Country Flag",
        )

        PlayerInfoColumn(player)
    }
}

@Composable
private fun PlayerInfoColumn(
    player: PlayerDisplayModel
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        PlayerNameRow(player)

        if (player.notes != null) {
            Text(
                text = player.notes,
                fontStyle = FontStyle.Italic,
            )
        }
    }
}

@Composable
private fun PlayerNameRow(player: PlayerDisplayModel) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = player.gamerTag,
        )

        Text(
            text = player.realName,
            fontStyle = FontStyle.Italic,
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
private fun PlayerListItemPreviewWithNotes() {
    val usFlag = FlagKit.getResId(LocalContext.current, "us")
    val player = PlayerDisplayModel(
        flagImage = UIImage.AndroidResource(usFlag),
        gamerTag = "Tstn",
        realName = "(Testing McTestFace)",
        notes = "(Coach)",
    )

    PocketLeagueTheme {
        Surface {
            PlayerListItem(
                player = player,
            )
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
private fun PlayerListItemPreviewWithoutNotes() {
    val usFlag = FlagKit.getResId(LocalContext.current, "us")
    val player = PlayerDisplayModel(
        flagImage = UIImage.AndroidResource(usFlag),
        gamerTag = "Tstn",
        realName = "(Testing McTestFace)",
        notes = null,
    )

    PocketLeagueTheme {
        Surface {
            PlayerListItem(
                player = player,
            )
        }
    }
}
