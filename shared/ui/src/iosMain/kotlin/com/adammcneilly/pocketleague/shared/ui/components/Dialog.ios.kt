package com.adammcneilly.pocketleague.shared.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.type
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupPositionProvider
import com.adammcneilly.pocketleague.shared.design.system.theme.PocketLeagueTheme
import org.jetbrains.skiko.SkikoKey

/**
 * An iOS implementation of a Dialog.
 *
 * Mostly copied from: https://github.com/chrisbanes/tivi/blob/main/common/ui/compose/src/iosMain/kotlin/app/tivi/common/compose/Dialog.kt
 */
@Composable
actual fun Dialog(
    onDismissRequest: () -> Unit,
    properties: DialogProperties,
    content: @Composable () -> Unit,
) {
    Popup(
        onDismissRequest = onDismissRequest,
        popupPositionProvider = IosPopupPositionProvider,
        focusable = true,
        onKeyEvent = { event ->
            if (properties.dismissOnBackPress && event.type == KeyEventType.KeyDown &&
                event.nativeKeyEvent.key == SkikoKey.KEY_ESCAPE
            ) {
                onDismissRequest()
                true
            } else {
                false
            }
        },
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize(),
        ) {
            var visible by remember { mutableStateOf(false) }

            LaunchedEffect(Unit) {
                visible = true
            }

            DialogBackground(
                visible = visible,
                properties = properties,
                onDismissRequest = onDismissRequest,
            )

            DialogContent(
                visible = visible,
                content = content,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DialogBackground(
    visible: Boolean,
    properties: DialogProperties,
    onDismissRequest: () -> Unit,
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(DrawerDefaults.scrimColor)
                .let { modifier ->
                    if (properties.dismissOnClickOutside) {
                        modifier.pointerInput(onDismissRequest) {
                            detectTapGestures(onTap = { onDismissRequest() })
                        }
                    } else {
                        modifier
                    }
                },
        )
    }
}

@Composable
@Suppress("MagicNumber")
private fun DialogContent(
    visible: Boolean,
    content: @Composable () -> Unit,
) {
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(initialOffsetY = { it / 6 }) + fadeIn(),
        exit = slideOutVertically(targetOffsetY = { it / 6 }) + fadeOut(),
        modifier = Modifier
            .padding(PocketLeagueTheme.sizes.screenPadding),
    ) {
        content()
    }
}

/**
 * An implementation of a [PopupPositionProvider] that just returns [IntOffset.Zero]. This is because our
 * popup basically takes up the whole screen (the background tint) and so we don't need to offset this popup.
 *
 * If we wanted to use something like a menu dropdown or tooltip, we would offset based on the item clicked.
 */
private object IosPopupPositionProvider : PopupPositionProvider {
    override fun calculatePosition(
        anchorBounds: IntRect,
        windowSize: IntSize,
        layoutDirection: LayoutDirection,
        popupContentSize: IntSize,
    ): IntOffset = IntOffset.Zero
}
