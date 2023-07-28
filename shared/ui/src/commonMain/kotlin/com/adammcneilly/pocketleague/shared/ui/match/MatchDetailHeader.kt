package com.adammcneilly.pocketleague.shared.ui.match

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchTeamResultDisplayModel
import com.adammcneilly.pocketleague.shared.design.system.theme.PocketLeagueTheme
import com.adammcneilly.pocketleague.shared.ui.components.CircleTeamLogo
import com.adammcneilly.pocketleague.shared.ui.placeholder.PlaceholderDefaults
import com.adammcneilly.pocketleague.shared.ui.placeholder.placeholderMaterial

/**
 * Renders the [displayModel] but only to show header information about
 * a Match. This renders team and event info inside a [Card] component.
 */
@Composable
fun MatchDetailHeader(
    displayModel: MatchDetailDisplayModel,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TeamResultsRow(displayModel = displayModel)

            Divider()

            Column(
                modifier = Modifier
                    .padding(PocketLeagueTheme.sizes.cardPadding)
                    .placeholderMaterial(
                        visible = displayModel.isPlaceholder,
                        color = PlaceholderDefaults.cardColor(),
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = displayModel.eventName,
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center,
                )

                Text(
                    text = displayModel.stageName,
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = displayModel.localDate,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelLarge,
                )
            }
        }
    }
}

@Composable
private fun TeamResultsRow(
    displayModel: MatchDetailDisplayModel,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
    ) {
        MatchTeamResultCell(
            displayModel = displayModel.blueTeamResult,
        )

        MatchTeamResultCell(
            displayModel = displayModel.orangeTeamResult,
        )
    }
}

@Composable
private fun MatchTeamResultCell(
    displayModel: MatchTeamResultDisplayModel,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .placeholderMaterial(
                visible = displayModel.team.isPlaceholder,
                color = PlaceholderDefaults.cardColor(),
            ),
    ) {
        CircleTeamLogo(
            displayModel = displayModel.team,
            modifier = Modifier
                .size(72.dp)
                .padding(8.dp),
        )

        Text(
            text = displayModel.score.toString(),
            style = MaterialTheme.typography.headlineMedium,
        )
    }
}
