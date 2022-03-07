package com.adammcneilly.pocketleague.dkmp.composables

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.SaveableStateHolder
import com.adammcneilly.pocketleague.shared.Navigation

/**
 * A Composable to define a single pane screen for [Navigation].
 */
@Composable
fun Navigation.OnePane(
    saveableStateHolder: SaveableStateHolder
) {
    Scaffold(
        topBar = { TopBar(getTitle(currentScreenIdentifier)) },
        content = {
            saveableStateHolder.SaveableStateProvider(currentScreenIdentifier.uri) {
                ScreenPicker(currentScreenIdentifier)
            }
        },
//        bottomBar = { if (currentScreenIdentifier.screen.navigationLevel == 1) Level1BottomBar(currentScreenIdentifier) }
    )
}
