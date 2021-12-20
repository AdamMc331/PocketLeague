package com.adammcneilly.pocketleague.phase.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination

/**
 * A screen for phase detail information managed by the supplied [viewModel].
 */
@Destination
@Composable
fun PhaseDetailScreen(
    phaseId: String,
    modifier: Modifier = Modifier,
    viewModel: PhaseDetailViewModel = hiltViewModel(),
) {
    Text(text = "Phase detail: $phaseId")
}
