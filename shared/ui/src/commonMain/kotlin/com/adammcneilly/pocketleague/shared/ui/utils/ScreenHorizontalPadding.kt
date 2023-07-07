package com.adammcneilly.pocketleague.shared.ui.utils

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import com.adammcneilly.pocketleague.shared.design.system.theme.PocketLeagueTheme

fun Modifier.screenHorizontalPadding() = composed {
    this.padding(
        horizontal = PocketLeagueTheme.sizes.screenPadding,
    )
}
