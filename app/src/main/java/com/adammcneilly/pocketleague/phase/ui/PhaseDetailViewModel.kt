package com.adammcneilly.pocketleague.phase.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adammcneilly.pocketleague.phase.domain.state.PhaseDetailAction
import com.adammcneilly.pocketleague.phase.domain.state.phaseDetailStateMutator
import com.adammcneilly.pocketleague.phase.domain.usecases.FetchPhaseDetailUseCase
import com.ramcosta.composedestinations.PhaseDetailScreenDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * A state management container for the [PhaseDetailScreen].
 */
@HiltViewModel
class PhaseDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    fetchPhaseDetailUseCase: FetchPhaseDetailUseCase,
) : ViewModel() {
    private val mutator = phaseDetailStateMutator(
        scope = viewModelScope,
        fetchPhaseDetailUseCase = fetchPhaseDetailUseCase,
    )

    val viewState = mutator.state

    private val navArgs = PhaseDetailScreenDestination.argsFrom(savedStateHandle)

    init {
        val fetchAction = PhaseDetailAction.FetchPhaseDetail(navArgs.phaseId)

        mutator.accept(fetchAction)
    }
}
