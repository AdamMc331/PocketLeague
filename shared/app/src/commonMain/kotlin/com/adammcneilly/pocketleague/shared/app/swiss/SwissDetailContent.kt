package com.adammcneilly.pocketleague.shared.app.swiss

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.core.displaymodels.SwissTeamResultDisplayModel
import com.adammcneilly.pocketleague.shared.ui.components.CircleTeamLogo
import com.adammcneilly.pocketleague.shared.ui.game.GameListItem
import com.adammcneilly.pocketleague.shared.ui.swiss.SwissTeamResultListCard
import com.adammcneilly.pocketleague.shared.ui.theme.PocketLeagueTheme
import com.adammcneilly.pocketleague.shared.ui.utils.VerticalSpacer

@Composable
fun SwissDetailContent(
    state: SwissDetailScreen.State,
    modifier: Modifier,
) {
    LazyColumn(
        contentPadding = PaddingValues(
            PocketLeagueTheme.sizes.screenPadding,
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier,
    ) {
        item {
            TeamOverviewCard(state.teamResult)
        }

        items(state.matches) { match ->
            Text(
                text = match.roundName,
                style = MaterialTheme.typography.headlineSmall,
            )

            VerticalSpacer(16.dp)

            RoundCard(
                swissMatch = match,
            )
        }
    }
}

@Composable
private fun RoundCard(
    swissMatch: SwissMatchDisplayModel,
) {
    Card(
        modifier = Modifier
            .clickable {
                // Navigate to Match detail
            },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterHorizontally),
        ) {
            CircleTeamLogo(
                displayModel = swissMatch.match.blueTeamResult.team,
                modifier = Modifier
                    .size(48.dp),
            )

            Text(
                text = "${swissMatch.match.blueTeamResult.score} - ${swissMatch.match.orangeTeamResult.score}",
                style = MaterialTheme.typography.headlineSmall,
            )

            CircleTeamLogo(
                displayModel = swissMatch.match.orangeTeamResult.team,
                modifier = Modifier
                    .size(48.dp),
            )
        }

        HorizontalDivider()

        swissMatch.games.forEachIndexed { index, game ->
            GameListItem(
                displayModel = game,
            )

            if (index != swissMatch.games.lastIndex) {
                HorizontalDivider()
            }
        }
    }
}

@Composable
private fun TeamOverviewCard(
    displayModel: SwissTeamResultDisplayModel,
) {
    SwissTeamResultListCard(
        teamResults = listOf(displayModel),
    )
}
