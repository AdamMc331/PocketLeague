package com.adammcneilly.pocketleague.eventoverview.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.PhaseDetailScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

/**
 * Displays the [EventOverviewContent] with the view state pulled from the supplied [viewModel].
 */
@Destination(
    navArgsDelegate = EventOverviewNavArgs::class,
)
@Composable
fun EventOverviewScreen(
    navigator: DestinationsNavigator,
    modifier: Modifier = Modifier,
    viewModel: EventOverviewViewModel = hiltViewModel(),
) {
    val viewState = viewModel.viewState.collectAsState()

    LaunchedEffect(viewState.value) {
        val selectedPhaseId = viewState.value.selectedPhaseId

        if (selectedPhaseId != null) {
            navigator.navigate(
                PhaseDetailScreenDestination(
                    phaseId = selectedPhaseId
                )
            )

            viewModel.navigatedToPhase()
        }
    }

    EventOverviewContent(
        viewState = viewState.value,
        onPhaseClicked = viewModel::phaseClicked,
        modifier = modifier,
    )
}
