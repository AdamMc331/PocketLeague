package com.adammcneilly.pocketleague.teamoverview.ui

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.R
import com.adammcneilly.pocketleague.core.ui.PocketLeagueImage
import com.adammcneilly.pocketleague.core.ui.UIImage
import com.adammcneilly.pocketleague.core.ui.theme.PocketLeagueTheme
import com.adammcneilly.pocketleague.player.ui.PlayerList

@Composable
fun TeamOverviewListItem(
    team: TeamOverviewDisplayModel,
    showRosterList: Boolean,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            PocketLeagueImage(
                team.logoImage(),
                contentDescription = "Team Logo",
                modifier = Modifier
                    .size(24.dp),
            )

            Text(
                text = team.name,
            )
        }

        if (showRosterList) {
            PlayerList(
                players = team.roster,
            )
        }
    }
}

@Composable
private fun TeamOverviewDisplayModel.logoImage(): UIImage {
    return if (isSystemInDarkTheme()) {
        this.darkLogoImage
    } else {
        this.lightLogoImage
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
private fun TeamOverviewListItemPreview() {
    val team = TeamOverviewDisplayModel(
        name = "Pittsburgh Knights",
        lightLogoImage = UIImage.Resource(R.drawable.us),
        roster = emptyList(),
    )

    PocketLeagueTheme {
        Surface {
            TeamOverviewListItem(
                team = team,
                showRosterList = false,
            )
        }
    }
}
