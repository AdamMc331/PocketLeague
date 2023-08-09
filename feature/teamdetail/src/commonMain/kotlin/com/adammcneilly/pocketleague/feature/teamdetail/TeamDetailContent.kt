package com.adammcneilly.pocketleague.feature.teamdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel
import com.adammcneilly.pocketleague.shared.design.system.theme.PocketLeagueTheme
import com.adammcneilly.pocketleague.shared.ui.components.CircleTeamLogo
import com.adammcneilly.pocketleague.shared.ui.utils.screenHorizontalPadding

/**
 * UI content for the team detail screen with the given [state].
 */
@Composable
internal fun TeamDetailContent(
    state: TeamDetailScreen.State,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(
            vertical = PocketLeagueTheme.sizes.screenPadding,
        ),
    ) {
        item {
            TeamHeader(
                team = state.team,
                modifier = Modifier
                    .screenHorizontalPadding(),
            )
        }
    }
}

@Composable
private fun TeamHeader(
    team: TeamOverviewDisplayModel,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(PocketLeagueTheme.sizes.listItemSpacing),
    ) {
        CircleTeamLogo(
            displayModel = team,
            modifier = Modifier
                .size(48.dp),
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(PocketLeagueTheme.sizes.textSpacing),
        ) {
            Text(
                text = team.name,
            )

            Text(
                text = team.region.name,
            )
        }
    }
}
