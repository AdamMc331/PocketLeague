package com.adammcneilly.pocketleague.android.designsystem.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

/**
 * Creates a [Card] composable that renders the supplied [text]. Useful for empty and error states.
 */
@Composable
fun EmptyStateCard(
    text: String,
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
    ) {
        Text(
            text = text,
            modifier = textModifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
        )
    }
}
