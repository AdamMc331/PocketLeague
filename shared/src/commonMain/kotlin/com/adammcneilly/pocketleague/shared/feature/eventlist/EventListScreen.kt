package com.adammcneilly.pocketleague.shared.feature.eventlist

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.shared.RocketLeagueEventsScreen
import com.adammcneilly.pocketleague.shared.ui.scaffold.PersistentScaffold
import com.adammcneilly.pocketleague.shared.ui.scaffold.navigation.components.PersistentNavigationBar
import com.adammcneilly.pocketleague.shared.ui.scaffold.navigation.components.PersistentNavigationRail
import com.adammcneilly.pocketleague.shared.ui.scaffold.rememberScaffoldState

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun EventListScreen(
    modifier: Modifier = Modifier,
) {
    rememberScaffoldState().PersistentScaffold(
        modifier = modifier,
        navigationBar = {
            PersistentNavigationBar()
        },
        navigationRail = {
            PersistentNavigationRail()
        },
        content = { scaffoldPadding ->
            RocketLeagueEventsScreen()
        },
    )
}
