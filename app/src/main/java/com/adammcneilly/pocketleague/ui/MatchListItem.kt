package com.adammcneilly.pocketleague.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.adammcneilly.pocketleague.shared.models.Match

/**
 * Displays a match between two teams inside a list item.
 */
@Composable
fun MatchListItem(
    match: Match,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = match.event.name,
            fontWeight = FontWeight.Bold,
        )

        Text(
            text = "TODO: Date",
            style = MaterialTheme.typography.caption,
        )
    }
}
