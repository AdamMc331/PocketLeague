package com.adammcneilly.pocketleague.android.designsystem.placeholder

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * In Material 3, a card uses the surface variant color as a background,
 * so anywhere we want to show a placeholder modifier on a card,
 * we need to pass that color. This is just a helper function
 * to do so.
 */
@Composable
fun Modifier.cardPlaceholder(
    visible: Boolean,
) = this.placeholderMaterial(
    visible = visible,
    color = PlaceholderDefaults.color(
        backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
    ),
)
