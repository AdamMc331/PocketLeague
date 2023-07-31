package com.adammcneilly.pocketleague.shared.app.eventdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.core.displaymodels.EventDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.shared.design.system.theme.PocketLeagueTheme
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

/**
 * UI representation of an [event].
 */
@Composable
fun EventDetailContent(
    event: EventDetailDisplayModel,
    matches: List<MatchDetailDisplayModel>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(
            PocketLeagueTheme.sizes.screenPadding,
        ),
        verticalArrangement = Arrangement.spacedBy(PocketLeagueTheme.sizes.listItemSpacing),
    ) {
        items(matches) { match ->
            MatchResultRow(match)
        }
    }
}

@Composable
private fun MatchResultRow(match: MatchDetailDisplayModel) {
    Card {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp),
        ) {
            KamelImage(
                resource = asyncPainterResource(match.blueTeamResult.team.imageUrl.lightThemeImageURL.orEmpty()),
                contentDescription = "Blue Team Image",
                modifier = Modifier
                    .size(36.dp),
            )

            Text(
                text = match.blueTeamResult.team.name,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1F),
                style = MaterialTheme.typography.labelSmall,
            )

            Text(
                text = "3 : 0",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1F),
            )

            Text(
                text = match.orangeTeamResult.team.name,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1F),
                style = MaterialTheme.typography.labelSmall,
            )

            KamelImage(
                resource = asyncPainterResource(match.orangeTeamResult.team.imageUrl.lightThemeImageURL.orEmpty()),
                contentDescription = "Orange Team Image",
                modifier = Modifier
                    .size(36.dp),
            )
        }
    }
}
