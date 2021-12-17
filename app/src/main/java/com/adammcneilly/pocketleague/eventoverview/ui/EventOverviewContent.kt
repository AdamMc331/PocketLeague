package com.adammcneilly.pocketleague.eventoverview.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.core.ui.CenteredMaterial3CircularProgressIndicator
import com.adammcneilly.pocketleague.core.ui.Material3Card
import com.adammcneilly.pocketleague.core.ui.getValue
import com.adammcneilly.pocketleague.core.ui.theme.PocketLeagueTheme
import com.adammcneilly.pocketleague.phase.ui.PhaseDisplayModel
import com.adammcneilly.pocketleague.phase.ui.PhaseList

/**
 * Displays the event overview information given the [viewState].
 */
@Composable
fun EventOverviewContent(
    viewState: EventOverviewViewState,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        when (viewState) {
            is EventOverviewViewState.Loading -> {
                CenteredMaterial3CircularProgressIndicator()
            }
            is EventOverviewViewState.Success -> {
                SuccessContent(
                    event = viewState.event,
                )
            }
            is EventOverviewViewState.Error -> {
                Text(
                    text = viewState.errorMessage.getValue(),
                )
            }
        }
    }
}

@Composable
private fun SuccessContent(
    event: EventOverviewDisplayModel
) {
    Column(
        modifier = Modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        SummaryHeaderLabel()

        Material3Card {
            EventOverviewHeader(
                event = event,
            )
        }

        BracketsHeaderLabel()

        PhaseList(
            phases = event.phases,
        )
    }
}

@Composable
private fun BracketsHeaderLabel() {
    Text(
        text = "Brackets",
        style = MaterialTheme.typography.headlineLarge,
    )
}

@Composable
private fun SummaryHeaderLabel() {
    Text(
        text = "Summary",
        style = MaterialTheme.typography.headlineLarge,
    )
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
private fun EventOverviewContentPreview() {
    val event = EventOverviewDisplayModel(
        eventName = "Main Event",
        phases = listOf(
            PhaseDisplayModel(
                phaseName = "Day 1: Swiss Matches",
                numPools = "1",
                bracketType = "Custom",
                numEntrants = "16",
            ),
            PhaseDisplayModel(
                phaseName = "Day 2-3: Single Elimination",
                numPools = "1",
                bracketType = "SE",
                numEntrants = "8",
            ),
        ),
        startDate = "November 12, 2021",
    )

    val viewState = EventOverviewViewState.Success(
        event = event,
    )

    PocketLeagueTheme {
        Surface {
            EventOverviewContent(viewState)
        }
    }
}
