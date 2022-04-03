package com.adammcneilly.pocketleague.eventoverview.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adammcneilly.pocketleague.shared.eventoverview.domain.GetEventOverviewUseCase
import com.adammcneilly.pocketleague.shared.eventoverview.state.EventOverviewAction
import com.adammcneilly.pocketleague.shared.eventoverview.state.eventOverviewStateMutator
import com.ramcosta.composedestinations.EventOverviewScreenDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * A state management container for the [EventOverviewScreen].
 */
@HiltViewModel
class EventOverviewViewModel @Inject constructor(
    getEventOverviewUseCase: GetEventOverviewUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val mutator = eventOverviewStateMutator(
        scope = viewModelScope,
        getEventOverviewUseCase = getEventOverviewUseCase,
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
     * Triggered whenever the user clicks on a phase section with the given [phaseId].
     */
    fun phaseClicked(phaseId: String) {
        val selectAction = EventOverviewAction.SelectPhase(phaseId = phaseId)

        mutator.accept(selectAction)
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
