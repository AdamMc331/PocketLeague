package com.adammcneilly.pocketleague.android.designsystem

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.paparazzi.Paparazzi
import com.adammcneilly.pocketleague.android.designsystem.theme.PocketLeagueTheme

/**
 * This is a helper function that renders all of our content inside a box that takes up max space,
 * and also wraps it around our design system theme.
 */
fun Paparazzi.snapshotScreen(
    content: @Composable () -> Unit,
) {
    this.snapshot {
        PocketLeagueTheme {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
            ) {
                content()
            }
        }
    }
}
