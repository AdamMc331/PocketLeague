package com.adammcneilly.pocketleague.core.ui

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

/**
 * A Material 3 version of a Card composable, until it's implemented by Google.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Material3Card(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.surfaceVariant,
    content: @Composable ColumnScope.() -> Unit,
) {
    Card(
        containerColor = color,
        modifier = modifier,
        content = content,
    )
}
