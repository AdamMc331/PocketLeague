package com.adammcneilly.pocketleague.shared.screens.eventdetail

import com.adammcneilly.pocketleague.feature.event.detail.EventDetailParams
import com.adammcneilly.pocketleague.feature.event.detail.EventDetailPresenter
import com.adammcneilly.pocketleague.feature.event.detail.EventDetailViewState
import com.adammcneilly.pocketleague.shared.screens.Navigation
import com.adammcneilly.pocketleague.shared.screens.ScreenInitSettings
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * Returns the [ScreenInitSettings] for our [com.adammcneilly.pocketleague.shared.screens.Screens.EventDetail] screen.
 */
fun Navigation.initEventDetail(
    params: EventDetailParams,
): ScreenInitSettings {
    return ScreenInitSettings(
        title = "Event Detail",
        initState = {
            EventDetailViewState(
                eventId = params.eventId,
            )
        },
        callOnInit = { stateManager ->
            // ARM - In the future, we ideally create one presenter per screen
            // and just observe when it's created.
            val presenter = EventDetailPresenter(
                eventRepository = events.appModule.dataModule.eventRepository,
                params = params,
            )

            stateManager.getScreenScope()?.let { screenScope ->
                presenter.init(screenScope)

                presenter
                    .state
                    .onEach { viewState ->
                        stateManager.updateScreen(EventDetailViewState::class) {
                            viewState
                        }
                    }
                    .launchIn(screenScope)
            }
        },
        reInitOnEachNavigation = false,
    )
}
