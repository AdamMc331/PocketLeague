package com.adammcneilly.pocketleague.dkmp.composables

import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

/**
 * A [SmallTopAppBar] for our application that displays the given [title].
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
    )
}
