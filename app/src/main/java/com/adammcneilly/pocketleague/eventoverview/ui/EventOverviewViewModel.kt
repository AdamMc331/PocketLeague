package com.adammcneilly.pocketleague.eventoverview.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adammcneilly.pocketleague.core.utils.DateTimeHelper
import com.adammcneilly.pocketleague.event.api.GetEventOverviewUseCase
import com.adammcneilly.pocketleague.eventoverview.domain.state.EventOverviewAction
import com.adammcneilly.pocketleague.eventoverview.domain.state.eventOverviewStateMutator
import com.ramcosta.composedestinations.EventOverviewScreenDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * A state management container for the [EventOverviewScreen].
 */
@HiltViewModel
class EventOverviewViewModel @Inject constructor(
    getEventOverviewUseCase: GetEventOverviewUseCase,
    dateTimeHelper: DateTimeHelper,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val mutator = eventOverviewStateMutator(
        scope = viewModelScope,
        getEventOverviewUseCase = getEventOverviewUseCase,
        dateTimeHelper = dateTimeHelper,
    )

    val viewState = mutator.state

    private val navArgs = EventOverviewScreenDestination.argsFrom(savedStateHandle)

    init {
        val fetchAction = EventOverviewAction.FetchEventOverview(
            eventId = navArgs.eventId,
        )

        mutator.accept(fetchAction)
    }

    /**
     * When we navigate to a phase, we should clear our selected phase so that we don't
     * continue to show it.
     */
    fun navigatedToPhase() {
        val navigatedAction = EventOverviewAction.NavigatedToPhaseDetail

        mutator.accept(navigatedAction)
    }
}
