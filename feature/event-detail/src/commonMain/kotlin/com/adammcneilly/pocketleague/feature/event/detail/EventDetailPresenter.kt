package com.adammcneilly.pocketleague.feature.event.detail

import com.adammcneilly.pocketleague.core.displaymodels.toDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.toOverviewDisplayModel
import com.adammcneilly.pocketleague.core.feature.PocketLeaguePresenter
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.data.event.EventRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

/**
 * An implementation of [PocketLeaguePresenter] to show detailed information
 * about an event.
 */
class EventDetailPresenter(
    private val eventRepository: EventRepository,
    private val params: EventDetailParams,
) : PocketLeaguePresenter<EventDetailViewState> {

    private val _state = MutableStateFlow(
        EventDetailViewState(
            eventId = params.eventId,
        )
    )
    override val state = _state.asStateFlow()

    override fun init(scope: CoroutineScope) {
        observeEventDetail(scope)
        observeEventParticipants(scope)
    }

    private fun observeEventParticipants(scope: CoroutineScope) {
        eventRepository
            .getEventParticipants(params.eventId)
            .onEach { teamList ->
                _state.update {
                    it.copy(
                        participants = teamList.map(Team::toOverviewDisplayModel)
                    )
                }
            }
            .launchIn(scope)
    }

    private fun observeEventDetail(scope: CoroutineScope) {
        eventRepository
            .getEvent(params.eventId)
            .onEach { event ->
                _state.update {
                    it.copy(
                        eventDetail = event.toDetailDisplayModel(),
                    )
                }
            }
            .launchIn(scope)
    }
}
