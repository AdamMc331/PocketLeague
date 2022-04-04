package com.adammcneilly.pocketleague.shared.phasedetail.state

import com.adammcneilly.pocketleague.shared.core.models.PhaseDetail
import com.adammcneilly.pocketleague.shared.core.ui.UIText
import com.adammcneilly.pocketleague.shared.phasedetail.domain.GetPhaseDetailUseCase
import com.adammcneilly.pocketleague.shared.phasedetail.ui.PhaseDetailDisplayModel
import com.tunjid.mutator.Mutation
import com.tunjid.mutator.coroutines.stateFlowMutator
import com.tunjid.mutator.coroutines.toMutationStream
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

/**
 * Create a [stateFlowMutator] that will be used to manage our [PhaseDetailViewState].
 */
fun phaseDetailStateMutator(
    scope: CoroutineScope,
    getPhaseDetailUseCase: GetPhaseDetailUseCase,
) = stateFlowMutator<PhaseDetailAction, PhaseDetailViewState>(
    scope = scope,
    initialState = PhaseDetailViewState(),
    actionTransform = { actions ->
        actions.toMutationStream {
            when (val action = type()) {
                is PhaseDetailAction.FetchPhaseDetail ->
                    action.flow
                        .fetchPhaseDetailMutations(getPhaseDetailUseCase)
            }
        }
    }
)

private fun Flow<PhaseDetailAction.FetchPhaseDetail>.fetchPhaseDetailMutations(
    getPhaseDetailUseCase: GetPhaseDetailUseCase,
): Flow<Mutation<PhaseDetailViewState>> {
    return this.flatMapLatest { action ->
        getPhaseDetailUseCase
            .invoke(action.phaseId)
            .map { result ->
                when (result) {
                    is GetPhaseDetailUseCase.Result.Success -> {
                        successMutation(
                            phaseDetail = result.phaseDetail,
                        )
                    }
                    is GetPhaseDetailUseCase.Result.Error -> {
                        errorMutation()
                    }
                }
            }
            .onStart {
                emit(loadingMutation())
            }
    }
}

private fun successMutation(
    phaseDetail: PhaseDetail,
) = Mutation<PhaseDetailViewState> {
    copy(
        showLoading = false,
        phase = phaseDetail.toDisplayModel(),
    )
}

private fun loadingMutation() = Mutation<PhaseDetailViewState> {
    copy(
        showLoading = true,
    )
}

private fun PhaseDetail.toDisplayModel(): PhaseDetailDisplayModel {
    return PhaseDetailDisplayModel(
        phaseName = this.name,
    )
}

private fun errorMutation() = Mutation<PhaseDetailViewState> {
    copy(
        showLoading = false,
        errorMessage = UIText.StringText(
            "Fetching phase detail failed.",
        ),
    )
}
