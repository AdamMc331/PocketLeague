package com.adammcneilly.pocketleague.phase.domain.state

import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.core.ui.UIText
import com.adammcneilly.pocketleague.phase.domain.models.PhaseDetail
import com.adammcneilly.pocketleague.phase.domain.usecases.FetchPhaseDetailUseCase
import com.adammcneilly.pocketleague.phase.ui.PhaseDetailDisplayModel
import com.adammcneilly.pocketleague.phase.ui.PhaseDetailViewState
import com.tunjid.mutator.Mutation
import com.tunjid.mutator.coroutines.stateFlowMutator
import com.tunjid.mutator.coroutines.toMutationStream
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow

/**
 * Create a [stateFlowMutator] that will be used to manage our [PhaseDetailViewState].
 */
fun phaseDetailStateMutator(
    scope: CoroutineScope,
    fetchPhaseDetailUseCase: FetchPhaseDetailUseCase,
) = stateFlowMutator<PhaseDetailAction, PhaseDetailViewState>(
    scope = scope,
    initialState = PhaseDetailViewState(),
    actionTransform = { actions ->
        actions.toMutationStream {
            when (val action = type()) {
                is PhaseDetailAction.FetchPhaseDetail ->
                    action.flow
                        .fetchPhaseDetailMutations(
                            fetchPhaseDetailUseCase,
                        )
            }
        }
    }
)

private fun Flow<PhaseDetailAction.FetchPhaseDetail>.fetchPhaseDetailMutations(
    fetchPhaseDetailUseCase: FetchPhaseDetailUseCase,
): Flow<Mutation<PhaseDetailViewState>> {
    return this.flatMapLatest { action ->
        flow {
            emitLoading()

            val result = fetchPhaseDetailUseCase.invoke(action.phaseId)

            when (result) {
                is Result.Success -> {
                    emitSuccess(
                        result.data,
                    )
                }
                is Result.Error -> {
                    emitError()
                }
            }
        }
    }
}

private suspend fun FlowCollector<Mutation<PhaseDetailViewState>>.emitSuccess(
    phaseDetail: PhaseDetail,
) = this.emit(
    Mutation {
        copy(
            showLoading = false,
            phase = phaseDetail.toDisplayModel(),
        )
    }
)

private suspend fun FlowCollector<Mutation<PhaseDetailViewState>>.emitError() = this.emit(
    Mutation {
        copy(
            showLoading = false,
            errorMessage = UIText.StringText(
                "Fetching phase detail failed.",
            ),
        )
    }
)

private suspend fun FlowCollector<Mutation<PhaseDetailViewState>>.emitLoading() = this.emit(
    Mutation {
        copy(
            showLoading = true,
        )
    }
)

private fun PhaseDetail.toDisplayModel(): PhaseDetailDisplayModel {
    return PhaseDetailDisplayModel(
        phaseName = this.name,
    )
}
