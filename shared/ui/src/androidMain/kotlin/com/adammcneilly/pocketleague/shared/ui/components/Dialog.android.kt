package com.adammcneilly.pocketleague.shared.ui.components

import androidx.compose.runtime.Composable

/**
 * Android implementation of a Dialog.
 */
@Composable
actual fun Dialog(
    onDismissRequest: () -> Unit,
    properties: DialogProperties,
    content: @Composable () -> Unit,
) {
    androidx.compose.ui.window.Dialog(
        onDismissRequest = onDismissRequest,
        properties = androidx.compose.ui.window.DialogProperties(
            dismissOnBackPress = properties.dismissOnBackPress,
            dismissOnClickOutside = properties.dismissOnClickOutside,
        ),
        content = content,
    )
}
