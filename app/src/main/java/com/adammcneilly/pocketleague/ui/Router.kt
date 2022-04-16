package com.adammcneilly.pocketleague.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.shared.screens.Navigation

@Composable
fun Navigation.Router() {
    val screenUIsStateHolder = rememberSaveableStateHolder()

    val twoPaneWidthThreshold = 1000.dp

    BoxWithConstraints() {
        if (maxWidth < maxHeight || maxWidth < twoPaneWidthThreshold) {
            OnePane(screenUIsStateHolder)
        } else {
            TwoPane(screenUIsStateHolder)
        }
    }

    screenStatesToRemove.forEach {
        screenUIsStateHolder.removeState(it.uri)
    }

    if (!only1ScreenInBackstack) {
        BackHandler {
            exitScreen()
        }
    }
}
