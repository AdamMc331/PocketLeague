package com.adammcneilly.pocketleague.ui.composables.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.adammcneilly.pocketleague.android.designsystem.components.Tooltip

/**
 * Follows the guidelines of a Material Design Chip.
 *
 * https://m3.material.io/components/chips/overview
 *
 * The popup work doesn't do well in multiplatform compose, so we allow the user to pass a [tooltip] optionally
 * if they want one.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TooltipChip(
    text: String,
    tooltipText: String
) {
    Box {
        val showTooltip = remember { mutableStateOf(false) }

        ElevatedAssistChip(
            onClick = {
                showTooltip.value = true
            },
            label = {
                Text(
                    text = text
                )
            }
        )

        Tooltip(showTooltip) {
            Text(text = tooltipText)
        }
    }
}
