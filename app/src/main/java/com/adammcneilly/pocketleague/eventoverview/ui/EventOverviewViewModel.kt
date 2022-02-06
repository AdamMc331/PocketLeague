package com.adammcneilly.pocketleague.eventoverview.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adammcneilly.pocketleague.core.utils.DateTimeHelper
import com.adammcneilly.pocketleague.eventoverview.domain.state.EventOverviewAction
import com.adammcneilly.pocketleague.eventoverview.domain.state.eventOverviewStateMutator
import com.adammcneilly.pocketleague.eventoverview.domain.usecases.FetchEventOverviewUseCase
import com.ramcosta.composedestinations.EventOverviewScreenDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * A state management container for the [EventOverviewScreen].
 */
@HiltViewModel
class EventOverviewViewModel @Inject constructor(
    fetchEventOverviewUseCase: FetchEventOverviewUseCase,
    dateTimeHelper: DateTimeHelper,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val mutator = eventOverviewStateMutator(
        scope = viewModelScope,
        fetchEventOverviewUseCase = fetchEventOverviewUseCase,
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
     * Whenever the user clicks on a phase with the given [phaseId], capture that as selected.
     */
    fun onPhaseClicked(phaseId: String) {
        val selectPhaseAction = EventOverviewAction.SelectPhase(
            phaseId = phaseId,
        )

        mutator.accept(selectPhaseAction)
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
