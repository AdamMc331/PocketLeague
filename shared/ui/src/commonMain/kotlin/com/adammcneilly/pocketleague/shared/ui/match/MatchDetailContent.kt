package com.adammcneilly.pocketleague.shared.ui.match

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.shared.design.system.theme.PocketLeagueTheme

/**
 * Compose content for the match detail screen.
 */
@Composable
fun MatchDetailContent(
    match: MatchDetailDisplayModel,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(PocketLeagueTheme.sizes.screenPadding),
    ) {
        item {
            MatchDetailHeader(
                displayModel = match,
            )
        }
    }
}
