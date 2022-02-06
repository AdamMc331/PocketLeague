package com.adammcneilly.pocketleague.phase.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.adammcneilly.pocketleague.core.ui.CenteredMaterial3CircularProgressIndicator
import com.adammcneilly.pocketleague.core.ui.getValue
import com.adammcneilly.pocketleague.core.ui.theme.PocketLeagueTheme

/**
 * Given a [viewState], render the UI for phase detail information.
 */
@Composable
fun PhaseDetailContent(
    viewState: PhaseDetailViewState,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
    ) {
        if (viewState.showLoading) {
            CenteredMaterial3CircularProgressIndicator()
        }

        if (viewState.phase != null) {
            SuccessContent(
                phase = viewState.phase,
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
    phase: PhaseDetailDisplayModel,
) {
    Text(
        text = phase.phaseName,
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
@Suppress("UnusedPrivateMember")
private fun PhaseDetailContentPreview() {
    val viewState = PhaseDetailViewState(
        showLoading = false,
        phase = PhaseDetailDisplayModel(
            phaseName = "Phase Name",
        ),
    )

    PocketLeagueTheme {
        Surface {
            PhaseDetailContent(
                viewState = viewState,
            )
        }
    }
}
