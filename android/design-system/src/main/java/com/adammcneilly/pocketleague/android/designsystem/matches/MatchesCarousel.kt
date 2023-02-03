package com.adammcneilly.pocketleague.android.designsystem.matches

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.android.designsystem.components.MatchCard
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel

private const val MATCH_CARD_WIDTH_RATIO = 0.8F

/**
 * Given a collection of [matches], render them in a horizontally
 * scrolling UI.
 */
@Composable
fun MatchesCarousel(
    matches: List<MatchDetailDisplayModel>,
    onMatchClicked: (String) -> Unit,
) {
    LazyRow(
        contentPadding = PaddingValues(
            horizontal = 16.dp,
        ),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(matches) { match ->
            MatchCard(
                match = match,
                onClick = onMatchClicked,
                modifier = Modifier
                    .fillParentMaxWidth(MATCH_CARD_WIDTH_RATIO),
            )
        }
    }
}
