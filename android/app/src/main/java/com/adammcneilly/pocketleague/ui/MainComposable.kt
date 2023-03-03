package com.adammcneilly.pocketleague.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.adammcneilly.pocketleague.shared.screens.DKMPViewModel
import com.adammcneilly.pocketleague.ui.sizeconfigs.ContentType
import com.adammcneilly.pocketleague.ui.sizeconfigs.NavigationType

/**
 * The main entry point of any compose based application.
 */
@Composable
fun MainComposable(
    viewModel: DKMPViewModel,
    navigationType: NavigationType,
    contentType: ContentType,
) {
    val appState by viewModel.stateFlow.collectAsState()
    val dkmpNav = appState.getNavigation(viewModel)
    dkmpNav.Router(
        navigationType = navigationType,
        contentType = contentType,
    )
}
