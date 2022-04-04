package com.adammcneilly.pocketleague.phase.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.shared.phasedetail.state.PhaseDetailViewState

/**
 * Given a [viewState], render the UI for phase detail information.
 */
@Composable
fun PhaseDetailContent(
    viewState: PhaseDetailViewState,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
    ) {
        Text(
            text = "Phase detail: ${viewState.phase?.phaseName}"
        )
    }
}
