package com.adammcneilly.pocketleague.phase.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.core.ui.Material3Card
import com.adammcneilly.pocketleague.core.ui.theme.PocketLeagueTheme

/**
 * Displays a collection of [phases].
 */
@Composable
fun PhaseList(
    phases: List<PhaseDisplayModel>,
    onPhaseClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        phases.forEach { phase ->
            Material3Card {
                PhaseListItem(
                    phase = phase,
                    onPhaseClicked = onPhaseClicked,
                )
            }
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
private fun PhaseListPreview() {
    val phases = listOf(
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
    )

    PocketLeagueTheme {
        Surface {
            PhaseList(
                phases = phases,
                onPhaseClicked = {},
            )
        }
    }
}
