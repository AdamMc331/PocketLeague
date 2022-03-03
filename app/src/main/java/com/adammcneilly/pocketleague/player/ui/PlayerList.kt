package com.adammcneilly.pocketleague.player.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.adammcneilly.pocketleague.core.ui.Material3Divider
import com.adammcneilly.pocketleague.core.ui.UIImage
import com.adammcneilly.pocketleague.android.design.theme.PocketLeagueTheme
import com.murgupluoglu.flagkit.FlagKit

/**
 * Given a list of [players], render that collection on the UI.
 */
@Composable
fun PlayerList(
    players: List<PlayerDisplayModel>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        players.map { player ->
            PlayerListItem(player = player)

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
private fun PlayerListPreview() {
    val caFlag = FlagKit.getResId(LocalContext.current, "ca")
    val usFlag = FlagKit.getResId(LocalContext.current, "us")

    val caFlagImage = UIImage.AndroidResource(caFlag)
    val usFlagImage = UIImage.AndroidResource(usFlag)

    val sosa = PlayerDisplayModel(
        flagImage = caFlagImage,
        gamerTag = "Sosa",
        realName = "(Testing McTestFace)",
        notes = null,
    )

    val kep = PlayerDisplayModel(
        flagImage = usFlagImage,
        gamerTag = "AlphaKep",
        realName = "(Testing McTestFace)",
        notes = null,
    )

    val omar = PlayerDisplayModel(
        flagImage = usFlagImage,
        gamerTag = "ElOmarMaton",
        realName = "(Testing McTestFace)",
        notes = null,
    )

    val dino = PlayerDisplayModel(
        flagImage = usFlagImage,
        gamerTag = "Dino",
        realName = "(Testing McTestFace)",
        notes = "(Substitute)",
    )

    val lando = PlayerDisplayModel(
        flagImage = usFlagImage,
        gamerTag = "McLando",
        realName = "(Testing McTestFace)",
        notes = "(Coach)",
    )

    val players = listOf(
        sosa,
        kep,
        omar,
        dino,
        lando,
    )

    PocketLeagueTheme {
        Surface {
            PlayerList(
                players = players,
            )
        }
    }
}
