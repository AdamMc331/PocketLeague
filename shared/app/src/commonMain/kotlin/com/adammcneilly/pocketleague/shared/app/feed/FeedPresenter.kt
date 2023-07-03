package com.adammcneilly.pocketleague.shared.app.feed

import androidx.compose.runtime.Composable
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter

/**
 * State management container for the [FeedScreen].
 */
class FeedPresenter(
    private val navigator: Navigator,
) : Presenter<FeedScreen.State> {

    @Composable
    override fun present(): FeedScreen.State {
        return FeedScreen.State(
            emptyList(),
            emptyList(),
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
