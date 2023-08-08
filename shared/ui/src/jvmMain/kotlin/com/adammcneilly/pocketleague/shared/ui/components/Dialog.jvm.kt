package com.adammcneilly.pocketleague.shared.ui.components

import androidx.compose.runtime.Composable

/**
 * Dialog implementation for JVM platforms.
 */
@Composable
actual fun Dialog(
    onDismissRequest: () -> Unit,
    properties: DialogProperties,
    content: @Composable () -> Unit,
) {
    // Need to figure out how to render a dialog on JVM.
    // TiVi uses something but I can't figure it out: https://github.com/chrisbanes/tivi/blob/main/common/ui/compose/src/jvmMain/kotlin/app/tivi/common/compose/Dialog.desktop.kt
    content()
}
