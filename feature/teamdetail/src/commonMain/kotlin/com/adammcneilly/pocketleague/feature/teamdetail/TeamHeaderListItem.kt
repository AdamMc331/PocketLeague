package com.adammcneilly.pocketleague.feature.teamdetail

import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel
import com.adammcneilly.pocketleague.shared.ui.components.CircleTeamLogo

/**
 * Component that renders overview information about a team (logo, name, region)
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TeamHeaderListItem(
    team: TeamOverviewDisplayModel,
    modifier: Modifier = Modifier,
) {
    ListItem(
        modifier = modifier,
        leadingContent = {
            CircleTeamLogo(
                displayModel = team,
                modifier = Modifier
                    .size(48.dp),
            )
        },
        headlineText = {
            Text(
                text = team.name,
            )
        },
        supportingText = {
            Text(
                text = team.region.name,
            )
        },
    )
}
