package com.adammcneilly.pocketleague.player.ui

import android.content.res.Configuration
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.adammcneilly.pocketleague.core.ui.theme.PocketLeagueTheme
import com.murgupluoglu.flagkit.FlagKit

@Composable
fun PlayerList(
    players: List<PlayerDisplayModel>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(players) { player ->
            PlayerListItem(player = player)

            Divider()
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
    val sosa = PlayerDisplayModel(
        flagResId = FlagKit.getResId(LocalContext.current, "us"),
        gamerTag = "Sosa",
        realName = "(Testing McTestFace)",
        notes = null,
    )

    val kep = PlayerDisplayModel(
        flagResId = FlagKit.getResId(LocalContext.current, "us"),
        gamerTag = "AlphaKep",
        realName = "(Testing McTestFace)",
        notes = null,
    )

    val omar = PlayerDisplayModel(
        flagResId = FlagKit.getResId(LocalContext.current, "us"),
        gamerTag = "ElOmarMaton",
        realName = "(Testing McTestFace)",
        notes = null,
    )

    val dino = PlayerDisplayModel(
        flagResId = FlagKit.getResId(LocalContext.current, "us"),
        gamerTag = "Dino",
        realName = "(Testing McTestFace)",
        notes = "(Substitute)",
    )

    val lando = PlayerDisplayModel(
        flagResId = FlagKit.getResId(LocalContext.current, "us"),
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
