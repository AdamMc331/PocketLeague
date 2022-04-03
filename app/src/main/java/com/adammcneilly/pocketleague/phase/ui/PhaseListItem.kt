package com.adammcneilly.pocketleague.phase.ui

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.android.design.theme.PocketLeagueTheme
import com.adammcneilly.pocketleague.shared.eventoverview.EventOverviewPhaseDisplayModel

private const val PHASE_INFO_WIDTH_PERCENTAGE = 0.75F

/**
 * Displays information about the supplied [phase].
 */
@Composable
fun PhaseListItem(
    phase: EventOverviewPhaseDisplayModel,
    onPhaseClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .clickable(
                onClick = {
                    onPhaseClicked.invoke(phase.phaseId)
                },
            )
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        PhaseNameLabel(phase)

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth(PHASE_INFO_WIDTH_PERCENTAGE),
        ) {
            InfoItemLabel(infoKey = "pools", infoValue = phase.numPools)

            InfoItemLabel(infoKey = "type", infoValue = phase.bracketType)

            InfoItemLabel(infoKey = "entrants", infoValue = phase.numEntrants)
        }
    }
}

@Composable
private fun PhaseNameLabel(phase: EventOverviewPhaseDisplayModel) {
    Text(
        text = phase.phaseName,
        style = MaterialTheme.typography.headlineSmall,
    )
}

@Composable
private fun InfoItemLabel(
    infoKey: String,
    infoValue: String,
) {
    Column {
        Text(
            text = infoValue,
        )

        Text(
            text = infoKey.toUpperCase(Locale.current),
            style = MaterialTheme.typography.labelSmall,
        )
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
private fun PhaseListItemPreview() {
    val phase = EventOverviewPhaseDisplayModel(
        phaseId = "123",
        phaseName = "Day 1: Swiss Matches",
        numPools = "1",
        bracketType = "Custom",
        numEntrants = "16",
    )

    PocketLeagueTheme {
        Surface {
            PhaseListItem(
                phase = phase,
                onPhaseClicked = {},
            )
        }
    }
}
