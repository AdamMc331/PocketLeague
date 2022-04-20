package com.adammcneilly.pocketleague.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.shared.screens.Navigation
import com.adammcneilly.pocketleague.shared.screens.ScreenIdentifier
import com.adammcneilly.pocketleague.shared.screens.Screens

/**
 * The screen picker tacks a current [screenIdentifier] and renders the content for that screen.
 */
@Composable
fun Navigation.ScreenPicker(
    screenIdentifier: ScreenIdentifier,
    paddingValues: PaddingValues = PaddingValues(),
) {

    when (screenIdentifier.screen) {
        Screens.Feed -> {
            FeedContent(
                viewState = stateProvider.get(screenIdentifier),
                modifier = Modifier
                    .padding(paddingValues),
            )
        }
        Screens.Stats -> {
            StatsContent(
                viewState = stateProvider.get(screenIdentifier),
                modifier = Modifier
                    .padding(paddingValues),
            )
        }
        Screens.Records -> {
            RecordsContent(
                viewState = stateProvider.get(screenIdentifier),
                modifier = Modifier
                    .padding(paddingValues),
            )
        }
    }
}
