package com.adammcneilly.pocketleague.composables.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp

/**
 * Follows the guidelines of a Material Design Chip.
 *
 * https://m3.material.io/components/chips/overview
 *
 * The popup work doesn't do well in multiplatform compose, so we allow the user to pass a [tooltip] optionally
 * if they want one.
 */
@Composable
fun Chip(
    text: String,
    leadingIcon: ImageVector? = null,
    tooltip: @Composable ((MutableState<Boolean>) -> Unit)? = null
) {
    Box {
        val showTooltip = remember { mutableStateOf(false) }

        ChipButton(
            onClick = {
                showTooltip.value = true
            },
            leadingIcon,
            text,
        )

        if (tooltip != null) {
            tooltip(showTooltip)
        }
    }
}

@Composable
private fun ChipButton(
    onClick: () -> Unit,
    leadingIcon: ImageVector?,
    text: String,
) {
    OutlinedButton(
        onClick = onClick,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        )
    ) {
        if (leadingIcon != null) {
            Icon(
                leadingIcon,
                contentDescription = null,
                modifier = Modifier.padding(end = 12.dp),
            )
        }

        Text(
            text = text.toUpperCase(Locale.current),
        )
    }
}
