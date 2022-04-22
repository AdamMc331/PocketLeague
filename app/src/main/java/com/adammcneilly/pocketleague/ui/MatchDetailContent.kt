package com.adammcneilly.pocketleague.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.adammcneilly.pocketleague.shared.models.Team
import com.adammcneilly.pocketleague.shared.screens.matchdetail.MatchDetailViewState
import com.google.accompanist.placeholder.material.placeholder

/**
 * The UI content of the match detail screen.
 */
@Composable
fun MatchDetailContent(
    viewState: MatchDetailViewState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            TeamNameLogo(
                team = viewState.match.blueTeam.team,
            )

            TeamNameLogo(
                team = viewState.match.orangeTeam.team,
            )
        }

        Spacer(modifier = Modifier.height(48.dp))

        viewState.games.forEachIndexed { index, game ->
            GameListItem(game = game)

            Divider()
        }
    }
}

@Composable
private fun RowScope.TeamNameLogo(
    team: Team,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .weight(1F),
    ) {
        Text(text = team.name)

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(team.imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = "Event Image",
            modifier = Modifier
                .size(48.dp)
                .placeholder(
                    visible = team.imageUrl == null,
                    shape = CircleShape,
                ),
        )
    }
}
