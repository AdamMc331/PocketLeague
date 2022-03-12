package com.adammcneilly.pocketleague.dkmp.composables

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.shared.Navigation

/**
 * A Router for [Navigation] that will be used to determine which screens should be rendered.
 */
@Composable
fun Navigation.Router(
    modifier: Modifier = Modifier,
) {

    val screenUIsStateHolder = rememberSaveableStateHolder()

    val twoPaneWidthThreshold = 1000.dp

    BoxWithConstraints(
        modifier = modifier,
    ) {
        if (maxWidth < maxHeight || maxWidth < twoPaneWidthThreshold) {
            OnePane(screenUIsStateHolder)
        } else {
            TwoPane(screenUIsStateHolder)
        }
    }

    screenStatesToRemove.forEach {
        screenUIsStateHolder.removeState(it.uri)
        println("PocketLeague: removed UI screen " + it.uri)
    }

    if (!only1ScreenInBackstack) {
        BackHandler {
            exitScreen()
        }
    }
}
