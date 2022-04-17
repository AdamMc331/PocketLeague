package com.adammcneilly.pocketleague.ui

import androidx.compose.runtime.Composable
import com.adammcneilly.pocketleague.shared.screens.Navigation
import com.adammcneilly.pocketleague.shared.screens.ScreenIdentifier
import com.adammcneilly.pocketleague.shared.screens.Screens

/**
 * The screen picker tacks a current [screenIdentifier] and renders the content for that screen.
 */
@Composable
fun Navigation.ScreenPicker(
    screenIdentifier: ScreenIdentifier,
) {

    when (screenIdentifier.screen) {
        Screens.Feed -> {
            FeedContent(
                viewState = stateProvider.get(screenIdentifier),
            )
        }
    }
}
