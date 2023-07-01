package com.adammcneilly.pocketleague.shared.design.system.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Special size properties that are used within the Pocket League application.
 *
 * @property[screenPadding] The padding between a screen component and the edge of the device
 * screen.
 * @property[listItemSpacing] The default spacing between items inside of a list.
 * @property[cardPadding] The spacing between text/components and the edge of a card component.
 */
@Immutable
data class PocketLeagueSizes(
    val screenPadding: Dp,
    val listItemSpacing: Dp,
    val cardPadding: Dp,
)

val LocalPocketLeagueSizes = staticCompositionLocalOf {
    PocketLeagueSizes(
        screenPadding = 0.dp,
        listItemSpacing = 0.dp,
        cardPadding = 0.dp,
    )
}
