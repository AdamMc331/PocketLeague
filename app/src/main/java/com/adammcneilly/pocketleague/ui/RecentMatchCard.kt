package com.adammcneilly.pocketleague.ui

import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.composables.recentmatch.RecentMatchCardContent
import com.adammcneilly.pocketleague.core.displaymodels.toDetailDisplayModel
import com.adammcneilly.pocketleague.core.models.Match

/**
 * Displays a match between two teams inside a list item.
 */
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun RecentMatchCard(
    match: Match,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
    ) {
        RecentMatchCardContent(
            match = match.toDetailDisplayModel(),
        )
    }
}
