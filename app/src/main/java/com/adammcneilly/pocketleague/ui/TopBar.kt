package com.adammcneilly.pocketleague.ui

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * This shows the top toolbar at the top of an application.
 */
@Composable
fun TopBar(
    title: String
) {
    SmallTopAppBar(
        title = {
            Text(
                text = title,
            )
        },
        modifier = Modifier
            .statusBarsPadding(),
    )
}
