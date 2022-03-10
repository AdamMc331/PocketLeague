package com.adammcneilly.pocketleague.dkmp.composables

import androidx.compose.runtime.Composable
import com.adammcneilly.pocketleague.shared.Navigation
import com.adammcneilly.pocketleague.shared.ScreenIdentifier
import com.adammcneilly.pocketleague.shared.Screens
import com.adammcneilly.pocketleague.shared.eventoverview.EventOverviewParams

/**
 * Given a [screenIdentifier], render that screen.
 */
@Composable
fun Navigation.ScreenPicker(
    screenIdentifier: ScreenIdentifier
) {

    when (screenIdentifier.screen) {
        Screens.EventSummaryList -> {
            EventSummaryListScreen(
                viewState = stateProvider.get(screenIdentifier),
                eventClicked = { eventId ->
                    navigate(
                        screen = Screens.EventOverview,
                        params = EventOverviewParams(eventId),
                    )
                },
                onSortChanged = {
                    // ...
                },
            )
        }
        Screens.EventOverview -> {
            EventOverviewScreen(state = stateProvider.get(screenIdentifier))
        }
    }
}
