package com.adammcneilly.pocketleague.shared.ui.match

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.shared.ui.theme.PocketLeagueTheme

private const val MATCH_CARD_WIDTH_RATIO = 0.8F

/**
 * Given a collection of [matches], render them in a horizontally
 * scrolling UI.
 */
@Composable
fun MatchCarousel(
    matches: List<MatchDetailDisplayModel>,
    contentPadding: PaddingValues,
    onMatchClicked: (Match.Id) -> Unit,
) {
    LazyRow(
        contentPadding = contentPadding,
        horizontalArrangement = Arrangement.spacedBy(PocketLeagueTheme.sizes.listItemSpacing),
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
