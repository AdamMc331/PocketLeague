package com.adammcneilly.pocketleague.shared.screens.feed

import com.adammcneilly.pocketleague.feature.feed.FeedPresenter
import com.adammcneilly.pocketleague.feature.feed.FeedViewState
import com.adammcneilly.pocketleague.shared.screens.Navigation
import com.adammcneilly.pocketleague.shared.screens.ScreenInitSettings
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * Returns the [ScreenInitSettings] for our [com.adammcneilly.pocketleague.shared.screens.Screens.Feed] screen.
 */
fun Navigation.initFeed(): ScreenInitSettings {
    return ScreenInitSettings(
        title = "Feed",
        initState = {
            FeedViewState()
        },
        callOnInit = { stateManager ->
            val presenter = FeedPresenter(
                events.appModule.dataModule.eventRepository,
                events.appModule.dataModule.matchRepository,
            )

            events.screenCoroutine { scope ->
                presenter.init(scope)

                presenter
                    .state
                    .onEach { viewState ->
                        stateManager.updateScreen(FeedViewState::class) {
                            viewState
                        }
                    }
                    .launchIn(scope)
            }
        },
        reInitOnEachNavigation = false,
    )
}
