package com.adammcneilly.pocketleague.shared.app.feed

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.toDetailDisplayModel
import com.adammcneilly.pocketleague.data.match.OctaneGGMatchService
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import kotlinx.coroutines.launch

/**
 * State management container for the [FeedScreen].
 */
class FeedPresenter(
    private val navigator: Navigator,
) : Presenter<FeedScreen.State> {

    @Composable
    override fun present(): FeedScreen.State {
        val coroutineScope = rememberCoroutineScope()
        var matches by remember {
            mutableStateOf(emptyList<MatchDetailDisplayModel>())
        }

        LaunchedEffect(Unit) {
            coroutineScope.launch {
                matches = OctaneGGMatchService()
                    .getPastWeeksMatches()
                    .getOrNull()
                    ?.map { it.toDetailDisplayModel() }
                    .orEmpty()
            }
        }

        return FeedScreen.State(
            recentMatches = matches,
            upcomingEvents = emptyList(),
        ) { event ->
            when (event) {
                is FeedScreen.Event.EventClicked -> {
                    // Handle event clicked
                }

                is FeedScreen.Event.MatchClicked -> {
                    // Handle match clicked
                }
            }
        }
    }
}
