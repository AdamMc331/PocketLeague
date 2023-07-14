package com.adammcneilly.pocketleague.shared.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable

/**
 * A Dialog is a piece of UI that appears on top of everything else
 * on the screen.
 */
@Composable
expect fun Dialog(
    onDismissRequest: () -> Unit,
    properties: DialogProperties,
    content: @Composable () -> Unit,
)

/**
 * Properties of a [Dialog] that configure certain properties we care about. Matches the same
 * properties set in an Android dialog by default.
 */
@Immutable
data class DialogProperties(
    val dismissOnBackPress: Boolean = true,
    val dismissOnClickOutside: Boolean = true,
)
