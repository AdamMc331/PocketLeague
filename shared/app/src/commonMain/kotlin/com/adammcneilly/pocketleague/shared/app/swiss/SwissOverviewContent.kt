package com.adammcneilly.pocketleague.shared.app.swiss

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.shared.ui.swiss.SwissTeamResultListCard

@Composable
fun SwissOverviewContent(
    state: SwissOverviewScreen.State,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        SwissTeamResultListCard(
            teamResults = state.results,
        )
    }
}
