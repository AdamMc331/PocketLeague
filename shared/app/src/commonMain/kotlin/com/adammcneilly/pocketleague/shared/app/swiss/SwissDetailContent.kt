package com.adammcneilly.pocketleague.shared.app.swiss

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.ThemedImageURL
import com.adammcneilly.pocketleague.shared.ui.components.CircleTeamLogo
import com.adammcneilly.pocketleague.shared.ui.theme.PocketLeagueTheme

@Composable
fun SwissDetailContent(
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
            TeamOverviewCard()
        }

        item {
            Text(
                text = "Round One",
                style = MaterialTheme.typography.headlineSmall,
            )
        }

        item {
            Text(
                text = "Round Two",
                style = MaterialTheme.typography.headlineSmall,
            )
        }

        item {
            Text(
                text = "Round Three",
                style = MaterialTheme.typography.headlineSmall,
            )
        }
    }
}

@Composable
private fun TeamOverviewCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(16.dp),
        ) {
            CircleTeamLogo(
                TeamOverviewDisplayModel.placeholder.copy(
                    imageUrl = ThemedImageURL(
                        "https://www.bing.com/th?pid=Sgg&qlt=100&u=https%3A%2F%2Fimages.start.gg%2Fimages%2Fteam%2F2218929%2Fimage-80964c6540ce4e270b39fa9e85ed991d-optimized.png&ehk=cwh1IIBL1XJtwDFfeWhJTm3xSEGdTTjJzG5kr6sYnXc%3D&w=160&h=160&r=0&c=3",
                    ),
                ),
                modifier = Modifier
                    .size(48.dp),
            )

            Column(
                modifier = Modifier
                    .weight(1F),
            ) {
                Text(
                    text = "G2 Stride",
                    style = MaterialTheme.typography.titleLarge,
                )

                Text(
                    text = "3 - 0 | 9 - 2 | +7",
                )
            }

            Text(
                text = "Qualified",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.labelLarge,
            )
        }
    }
}
