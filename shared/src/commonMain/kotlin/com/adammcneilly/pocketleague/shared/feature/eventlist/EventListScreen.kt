package com.adammcneilly.pocketleague.shared.feature.eventlist

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.shared.RocketLeagueEventsScreen
import com.adammcneilly.pocketleague.shared.ui.scaffold.PersistentScaffold
import com.adammcneilly.pocketleague.shared.ui.scaffold.rememberScaffoldState

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun EventListScreen(
    modifier: Modifier = Modifier,
) {
    rememberScaffoldState().PersistentScaffold(
        modifier = modifier,
        content = { _ ->
            RocketLeagueEventsScreen()
        },
    )
}
