package com.adammcneilly.pocketleague.feature.teamdetail

import androidx.compose.foundation.layout.size
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel
import com.adammcneilly.pocketleague.shared.ui.components.CircleTeamLogo

/**
 * Component that renders overview information about a team (logo, name, region)
 */
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
        headlineContent = {
            Text(
                text = team.name,
                style = MaterialTheme.typography.headlineMedium,
            )
        },
        supportingContent = {
            Text(
                text = team.region.name,
                style = MaterialTheme.typography.titleMedium,
            )
        },
    )
}
