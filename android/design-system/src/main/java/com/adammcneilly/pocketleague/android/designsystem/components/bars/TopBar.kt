package com.adammcneilly.pocketleague.android.designsystem.components.bars

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * This shows the top toolbar of an application.
 *
 * @param[title] The text to display inside this toolbar.
 * @param[modifier] Optional modifications to perform on this component.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            Text(
                text = title,
            )
        },
        modifier = modifier,
    )
}
