package com.adammcneilly.pocketleague.shared.app

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.feature.eventdetail.EventDetailScreen
import com.adammcneilly.pocketleague.feature.teamdetail.TeamDetailScreen
import com.adammcneilly.pocketleague.shared.app.bars.PLTopAppBar
import com.adammcneilly.pocketleague.shared.app.feed.FeedScreen
import com.adammcneilly.pocketleague.shared.app.match.MatchDetailScreen
import com.adammcneilly.pocketleague.shared.design.system.theme.PocketLeagueTheme
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.push

/**
 * Main composable entrypoint to the shared multiplatform version of
 * the pocket league app.
 */
@Composable
fun PocketLeagueApp(
    modifier: Modifier = Modifier,
) {
    PocketLeagueTheme {
        // In the future, we may want to look at some DI framework to make it easy to pass
        // in all of these factories, or even consider the codegen that Circuit offers.
        val circuitConfig = Circuit.Builder()
            .addUiFactories(
                listOf(
                    FeedScreen.UiFactory,
                    MatchDetailScreen.UiFactory,
                    EventDetailScreen.UiFactory,
                    TeamDetailScreen.UiFactory,
                ),
            )
            .addPresenterFactories(
                listOf(
                    FeedScreen.PresenterFactory,
                    MatchDetailScreen.PresenterFactory,
                    EventDetailScreen.PresenterFactory(
                        navigateToMatch = { navigator, matchId ->
                            navigator.goTo(MatchDetailScreen(matchId.id))
                        },
                    ),
                    TeamDetailScreen.PresenterFactory,
                ),
            )
            .build()

        CircuitCompositionLocals(circuitConfig) {
            val backstack = rememberSaveableBackStack {
                push(FeedScreen)
            }

            val navigator = provideCircuitNavigator(backstack) {
                println("Is this being called?")
                // In the future, we need to handle a back press when we are at the root
                // screen (probably just close the app?)
            }

            Surface(
                modifier = modifier,
            ) {
                Column {
                    PLTopAppBar(
                        text = "Pocket League",
                        showBack = (backstack.size > 1),
                        onBackClicked = {
                            navigator.pop()
                        },
                    )

                    NavigableCircuitContent(
                        navigator = navigator,
                        backstack = backstack,
                        modifier = Modifier
                            .weight(1F),
                    )
                }
            }
        }
    }
}
