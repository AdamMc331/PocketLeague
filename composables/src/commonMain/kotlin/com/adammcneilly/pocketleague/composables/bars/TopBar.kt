package com.adammcneilly.pocketleague.composables.bars

import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * This shows the top toolbar of an application.
 *
 * @param[title] The text to display inside this toolbar.
 * @param[modifier] Optional modifications to perform on this component.
 */
@Composable
fun TopBar(
    title: String,
    modifier: Modifier = Modifier,
) {
    SmallTopAppBar(
        title = {
            Text(
                text = title,
            )
        },
        modifier = modifier,
    )
}
