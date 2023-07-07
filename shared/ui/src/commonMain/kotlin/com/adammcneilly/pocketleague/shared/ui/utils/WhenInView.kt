package com.adammcneilly.pocketleague.shared.ui.utils

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.layout.findRootCoordinates
import androidx.compose.ui.layout.onGloballyPositioned

/**
 * This is a custom [Modifier] that observes whether or not a
 * composable has been rendered on the screen, and once it has
 * it will execute the given [callback].
 */
fun Modifier.whenInView(
    callback: () -> Unit,
): Modifier = composed {
    var hasBeenShown by remember { mutableStateOf(false) }

    if (hasBeenShown) {
        Modifier
    } else {
        Modifier.onGloballyPositioned {
            val root = it.findRootCoordinates()
            val visible = !root.localBoundingBoxOf(it, clipBounds = true).isEmpty

            if (visible) {
                hasBeenShown = true
                callback.invoke()
            }
        }
    }
}
