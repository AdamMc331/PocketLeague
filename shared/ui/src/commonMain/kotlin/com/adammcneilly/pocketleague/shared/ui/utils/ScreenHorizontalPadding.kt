package com.adammcneilly.pocketleague.shared.ui.utils

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import com.adammcneilly.pocketleague.shared.design.system.theme.PocketLeagueTheme

/**
 * A custom [Modifier] that applies the screen padding as the horizontal
 * padding for some component.
 *
 * This is useful for situations like the feed screen, where the content is shown
 * in a lazy list, and we don't want to apply horizontal padding to the list itself
 * since that will cut off any carousels on the screen.
 */
fun Modifier.screenHorizontalPadding() = composed {
    this.padding(
        horizontal = PocketLeagueTheme.sizes.screenPadding,
    )
}
