package com.adammcneilly.pocketleague.shared.ui.theme

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
 * @property[textSpacing] When two text components are placed on top of each other in a column,
 * we may want to apply this so they don't seem too cramped.
 */
@Immutable
data class Sizes(
    val screenPadding: Dp,
    val listItemSpacing: Dp,
    val cardPadding: Dp,
    val textSpacing: Dp,
)

val LocalPocketLeagueSizes = staticCompositionLocalOf {
    Sizes(
        screenPadding = 0.dp,
        listItemSpacing = 0.dp,
        cardPadding = 0.dp,
        textSpacing = 0.dp,
    )
}
