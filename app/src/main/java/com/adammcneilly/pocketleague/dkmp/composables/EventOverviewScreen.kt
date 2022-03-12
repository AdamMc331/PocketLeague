package com.adammcneilly.pocketleague.dkmp.composables

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
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.R
import com.adammcneilly.pocketleague.core.ui.CenteredMaterial3CircularProgressIndicator
import com.adammcneilly.pocketleague.core.ui.Material3Card
import com.adammcneilly.pocketleague.core.ui.TextCard
import com.adammcneilly.pocketleague.eventoverview.ui.EventOverviewHeader
import com.adammcneilly.pocketleague.phase.ui.PhaseList
import com.adammcneilly.pocketleague.shared.eventoverview.EventOverviewDisplayModel
import com.adammcneilly.pocketleague.shared.eventoverview.EventOverviewState
import com.adammcneilly.pocketleague.standings.ui.StandingsList

/**
 * Displays event overview information.
 */
@Composable
fun EventOverviewScreen(
    viewState: EventOverviewState,
    onPhaseClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            if (viewState.showLoading) {
                CenteredMaterial3CircularProgressIndicator()
            }

            val event = viewState.event

            if (event != null) {
                SuccessContent(
                    event = event,
                    onPhaseClicked = onPhaseClicked,
                )
            }

            val errorMessage = viewState.errorMessage

            if (errorMessage != null) {
                Text(
                    text = errorMessage,
                )
            }
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
