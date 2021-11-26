package com.adammcneilly.pocketleague.teamdetail.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.R
import com.adammcneilly.pocketleague.core.ui.PocketLeagueImage
import com.adammcneilly.pocketleague.core.ui.UIImage
import com.adammcneilly.pocketleague.core.ui.theme.PocketLeagueTheme
import com.adammcneilly.pocketleague.player.ui.PlayerDisplayModel
import com.adammcneilly.pocketleague.player.ui.PlayerList
import com.murgupluoglu.flagkit.FlagKit

/**
 * The UI content of the team detail screen based on the supplied [team].
 */
@Composable
fun TeamDetailContent(
    team: TeamDetailDisplayModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        PocketLeagueImage(
            image = team.logo,
            contentDescription = "Team Logo",
            modifier = Modifier
                .size(120.dp),
        )

        Text(
            text = team.name,
            style = MaterialTheme.typography.headlineLarge,
        )

        PlayerList(team.players)
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
private fun TeamDetailContentPreview() {
    val caFlag = FlagKit.getResId(LocalContext.current, "ca")
    val usFlag = FlagKit.getResId(LocalContext.current, "us")

    val caFlagImage = UIImage.Resource(caFlag)
    val usFlagImage = UIImage.Resource(usFlag)

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

    val teamDetail = TeamDetailDisplayModel(
        name = "Pittsburgh Knights",
        logo = UIImage.Resource(R.drawable.us),
        players = players,
    )

    PocketLeagueTheme {
        Surface {
            TeamDetailContent(team = teamDetail)
        }
    }
}
