package com.adammcneilly.pocketleague.feature.teamdetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.paparazzi.Paparazzi
import com.adammcneilly.pocketleague.shared.ui.theme.PocketLeagueTheme

/**
 * This is a helper function that renders all of our content inside a box that takes up max space,
 * and also wraps it around our design system theme.
 */
fun Paparazzi.snapshotScreen(
    useDarkTheme: Boolean,
    screenPaddingDp: Int = 16,
    content: @Composable () -> Unit,
) {
    this.snapshot {
        PocketLeagueTheme(
            useDarkTheme = useDarkTheme,
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(screenPaddingDp.dp),
                ) {
                    content()
                }
            }
        }
    }
}
