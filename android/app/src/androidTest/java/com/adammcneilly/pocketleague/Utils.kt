package com.adammcneilly.pocketleague

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import com.adammcneilly.pocketleague.shared.design.system.theme.PocketLeagueTheme

/**
 * Helper function that renders the supplied [content] inside a [PocketLeagueTheme].
 */
fun ComposeContentTestRule.setPocketLeagueContent(
    content: @Composable () -> Unit,
) {
    this.setContent {
        PocketLeagueTheme {
            content()
        }
    }
}
