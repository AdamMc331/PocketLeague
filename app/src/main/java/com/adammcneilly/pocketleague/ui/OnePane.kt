package com.adammcneilly.pocketleague.ui

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.SaveableStateHolder
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.shared.screens.Navigation
import com.google.accompanist.insets.systemBarsPadding

/**
 * A [OnePane] layout is used any time we want to show a single piece of content, typically on phones or other compact screens.
 */
@Composable
fun Navigation.OnePane(
    saveableStateHolder: SaveableStateHolder,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            TopBar(getTitle(currentScreenIdentifier))
        },
        content = { paddingValues ->
            saveableStateHolder.SaveableStateProvider(currentScreenIdentifier.uri) {
                ScreenPicker(
                    currentScreenIdentifier,
                    paddingValues = paddingValues,
                )
            }
        },
        bottomBar = {
            if (currentScreenIdentifier.screen.navigationLevel == 1) {
                Level1BottomBar(currentScreenIdentifier)
            }
        },
        modifier = modifier
            .systemBarsPadding(),
    )
}
