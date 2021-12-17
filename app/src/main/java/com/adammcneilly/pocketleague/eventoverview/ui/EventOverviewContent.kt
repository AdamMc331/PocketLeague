package com.adammcneilly.pocketleague.eventoverview.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.core.ui.Material3Card
import com.adammcneilly.pocketleague.core.ui.theme.PocketLeagueTheme
import com.adammcneilly.pocketleague.phase.ui.PhaseDisplayModel
import com.adammcneilly.pocketleague.phase.ui.PhaseList

/**
 * Displays overview information about an [event] including its info, phases, and standings.
 */
@Composable
fun EventOverviewContent(
    event: EventOverviewDisplayModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        SummaryHeaderLabel()

        EventOverviewHeader(event)

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
private fun EventOverviewHeader(event: EventOverviewDisplayModel) {
    Material3Card {
        EventOverviewHeader(
            event = event,
        )
    }
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
    )

    PocketLeagueTheme {
        Surface {
            EventOverviewContent(event = event)
        }
    }
}
