package com.adammcneilly.pocketleague.shared.phasedetail.state

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
) = stateFlowMutator<PhaseDetailAction, PhaseDetailViewState>(
    scope = scope,
    initialState = PhaseDetailViewState(),
    actionTransform = { actions ->
        actions.toMutationStream {
            when (val action = type()) {
                is PhaseDetailAction.FetchPhaseDetail ->
                    action.flow
                        .fetchPhaseDetailMutations()
            }
        }
    }
)

private fun Flow<PhaseDetailAction.FetchPhaseDetail>.fetchPhaseDetailMutations(): Flow<Mutation<PhaseDetailViewState>> {
    return this.flatMapLatest { action ->
        flow {
            emitLoading()

            // Loading coming soon
        }
    }
}

private suspend fun FlowCollector<Mutation<PhaseDetailViewState>>.emitLoading() = this.emit(
    Mutation {
        copy(
            showLoading = true,
        )
    }
)
