package com.adammcneilly.pocketleague.shared.app.swiss

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.shared.ui.swiss.SwissTeamResultListCard
import com.adammcneilly.pocketleague.shared.ui.theme.PocketLeagueTheme

@Composable
fun SwissOverviewContent(
    state: SwissOverviewScreen.State,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        contentPadding = PaddingValues(PocketLeagueTheme.sizes.screenPadding),
        modifier = modifier,
    ) {
        item {
            SwissTeamResultListCard(
                teamResults = state.results,
            )
        }
    }
}
