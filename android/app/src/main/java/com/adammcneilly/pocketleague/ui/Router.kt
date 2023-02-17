package com.adammcneilly.pocketleague.ui

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import com.adammcneilly.pocketleague.shared.screens.Navigation
import com.adammcneilly.pocketleague.ui.sizeconfigs.ContentType
import com.adammcneilly.pocketleague.ui.sizeconfigs.NavigationType

/**
 * The Router is used to track screen states and show a [OnePane] or [TwoPane] layout based on
 * available space.
 */
@Composable
fun Navigation.Router(
    navigationType: NavigationType,
    contentType: ContentType,
) {
    val screenUIsStateHolder = rememberSaveableStateHolder()

    PocketLeagueScaffold(
        navigationType = navigationType,
        contentType = contentType,
        saveableStateHolder = screenUIsStateHolder,
    )

    screenStatesToRemove.forEach {
        screenUIsStateHolder.removeState(it.uri)
    }

    if (!only1ScreenInBackstack) {
        BackHandler {
            exitScreen()
        }
    }
}
