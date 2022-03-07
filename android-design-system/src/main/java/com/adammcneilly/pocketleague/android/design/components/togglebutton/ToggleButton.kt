package com.adammcneilly.pocketleague.android.design.components.togglebutton

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp

/**
 * A single button component that appears inside of our [ToggleButtonRow].
 *
 * @param[shape] The bounding shape of the button. This will be rounded at the ends, but flat in the
 * middle.
 * @param[onClick] A callback invoked when the user clicks on this button.
 * @param[text] The text to display inside this button.
 * @param[selected] True if this is the currently selected option in the button row, false otherwise.
 * @param[modifier] An optional [Modifier] to configure this button.
 */
@Composable
fun ToggleButton(
    shape: Shape,
    onClick: () -> Unit,
    text: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
) {
    val colorsToUse = if (selected) {
        ButtonDefaults.outlinedButtonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        )
    } else {
        ButtonDefaults.outlinedButtonColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground,
        )
    }

    val borderColor = if (selected) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.outline
    }

    OutlinedButton(
        onClick = onClick,
        shape = shape,
        colors = colorsToUse,
        modifier = modifier,
        border = BorderStroke(
            width = 1.dp,
            color = borderColor,
        )
    ) {
        Text(
            text = text.toUpperCase(Locale.current),
        )
    }
}