package com.adammcneilly.pocketleague.android.designsystem.matches

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.android.designsystem.components.EmptyStateCard

/**
 * Renders an [EmptyStateCard] for when there are no recent matches found.
 */
@Composable
fun RecentMatchesEmptyState() {
    EmptyStateCard(
        text = "There are no recent match results.",
        modifier = Modifier
            .padding(
                horizontal = 16.dp,
            ),
        textModifier = Modifier
            .padding(32.dp),
    )
}
