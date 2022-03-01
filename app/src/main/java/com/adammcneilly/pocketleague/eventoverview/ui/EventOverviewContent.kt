package com.adammcneilly.pocketleague.eventoverview.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.ExcludeFromJacocoGeneratedReport
import com.adammcneilly.pocketleague.R
import com.adammcneilly.pocketleague.core.ui.CenteredMaterial3CircularProgressIndicator
import com.adammcneilly.pocketleague.core.ui.Material3Card
import com.adammcneilly.pocketleague.core.ui.TextCard
import com.adammcneilly.pocketleague.core.ui.getValue
import com.adammcneilly.pocketleague.core.ui.theme.PocketLeagueTheme
import com.adammcneilly.pocketleague.phase.ui.PhaseDisplayModel
import com.adammcneilly.pocketleague.phase.ui.PhaseList
import com.adammcneilly.pocketleague.standings.ui.StandingsDisplayModel
import com.adammcneilly.pocketleague.standings.ui.StandingsList
import com.adammcneilly.pocketleague.standings.ui.StandingsPlacementDisplayModel

/**
 * Displays the event overview information given the [viewState].
 */
@Composable
fun EventOverviewContent(
    viewState: EventOverviewViewState,
    onPhaseClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
    ) {
        if (viewState.showLoading) {
            CenteredMaterial3CircularProgressIndicator()
        }

        if (viewState.event != null) {
            SuccessContent(
                event = viewState.event,
                onPhaseClicked = onPhaseClicked,
            )
        }

        if (viewState.errorMessage != null) {
            Text(
                text = viewState.errorMessage.getValue(),
            )
        }
    }
}

@Composable
private fun SuccessContent(
    event: EventOverviewDisplayModel,
    onPhaseClicked: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        HeaderLabel(
            text = stringResource(R.string.summary),
        )

        Material3Card {
            EventOverviewHeader(
                event = event,
            )
        }

        HeaderLabel(
            text = stringResource(R.string.brackets),
        )

        if (event.phases.isNotEmpty()) {
            PhaseList(
                phases = event.phases,
                onPhaseClicked = onPhaseClicked,
            )
        } else {
            TextCard(
                text = stringResource(R.string.bracket_information_unavailable),
            )
        }

        HeaderLabel(
            text = stringResource(R.string.standings),
        )

        if (event.standings.placements.isNotEmpty()) {
            Material3Card {
                StandingsList(
                    standings = event.standings,
                )
            }
        } else {
            TextCard(
                text = stringResource(R.string.standings_information_unavailable),
            )
        }
    }
}

@Composable
private fun HeaderLabel(
    text: String,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineMedium,
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
@Suppress("LongMethod")
@ExcludeFromJacocoGeneratedReport
private fun EventOverviewContentPreview() {
    val placements = (1..10).map {
        StandingsPlacementDisplayModel(
            placement = it.toString(),
            teamName = "Pittsburgh Knights",
            roster = "sosa / AlphaKep / ElOmarMaton",
            teamLogo = null,
        )
    }

    val standings = StandingsDisplayModel(
        placements = placements,
    )

    val event = EventOverviewDisplayModel(
        eventName = "Main Event",
        phases = listOf(
            PhaseDisplayModel(
                phaseId = "123",
                phaseName = "Day 1: Swiss Matches",
                numPools = "1",
                bracketType = "Custom",
                numEntrants = "16",
            ),
            PhaseDisplayModel(
                phaseId = "123",
                phaseName = "Day 2-3: Single Elimination",
                numPools = "1",
                bracketType = "SE",
                numEntrants = "8",
            ),
        ),
        startDate = "November 12, 2021",
        standings = standings,
    )

    val viewState = EventOverviewViewState(
        showLoading = false,
        event = event,
    )

    PocketLeagueTheme {
        Surface {
            EventOverviewContent(
                viewState = viewState,
                onPhaseClicked = {},
            )
        }
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
@Suppress("LongMethod")
@ExcludeFromJacocoGeneratedReport
private fun EventOverviewUpcomingContentPreview() {
    val standings = StandingsDisplayModel(
        placements = emptyList(),
    )

    val event = EventOverviewDisplayModel(
        eventName = "Main Event",
        phases = emptyList(),
        startDate = "November 12, 2021",
        standings = standings,
    )

    val viewState = EventOverviewViewState(
        showLoading = false,
        event = event,
    )

    PocketLeagueTheme {
        Surface {
            EventOverviewContent(
                viewState = viewState,
                onPhaseClicked = {},
            )
        }
    }
}
