package com.adammcneilly.pocketleague.ui

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.SaveableStateHolder
import com.adammcneilly.pocketleague.shared.screens.Navigation

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
        bottomBar = {
            if (currentScreenIdentifier.screen.navigationLevel == 1) {
                Level1BottomBar(currentScreenIdentifier)
            }
        },
    )
}
