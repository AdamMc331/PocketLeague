package com.adammcneilly.pocketleague.phase.domain.state

import app.cash.turbine.test
import com.adammcneilly.pocketleague.bracket.domain.models.BracketType
import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.phase.data.FakePhaseService
import com.adammcneilly.pocketleague.phase.domain.models.PhaseDetail
import com.adammcneilly.pocketleague.phase.domain.usecases.FetchPhaseDetailUseCaseImpl
import com.adammcneilly.pocketleague.phase.ui.PhaseDetailDisplayModel
import com.adammcneilly.pocketleague.phase.ui.PhaseDetailViewState
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runTest
import org.junit.Test

class PhaseDetailStateMutatorTest {
    private val fakePhaseService = FakePhaseService()
    private val fetchPhaseDetailUseCase = FetchPhaseDetailUseCaseImpl(
        service = fakePhaseService,
    )

    private val mutator = phaseDetailStateMutator(
        scope = TestCoroutineScope(),
        fetchPhaseDetailUseCase = fetchPhaseDetailUseCase,
    )

    @Test
    fun fetchPhaseDetailSuccess() = runTest {
        val phaseId = "123"

        val phaseDetail = PhaseDetail(
            id = phaseId,
            groupId = "Group ID",
            numPools = 1,
            numEntrants = 1,
            name = "Phase Name",
            phaseOrder = 1,
            bracketType = BracketType.SINGLE_ELIMINATION,
            sets = emptyList(),
        )

        val fakePhaseResult = Result.Success(phaseDetail)

        fakePhaseService.mockPhaseDetailResponse(
            phaseId = phaseId,
            response = fakePhaseResult,
        )

        // Expectations
        val initialState = PhaseDetailViewState()
        val expectedPhaseDetailDisplayModel = PhaseDetailDisplayModel(
            phaseName = phaseDetail.name,
        )
        val successState = initialState.copy(
            showLoading = false,
            phase = expectedPhaseDetailDisplayModel,
        )

        mutator.state
            .test {
                mutator.accept(PhaseDetailAction.FetchPhaseDetail(phaseId))

                val expectedStates = listOf(initialState, successState)

                expectedStates.forEach { state ->
                    assertThat(state).isEqualTo(awaitItem())
                }

                cancelAndIgnoreRemainingEvents()
            }
    }
}
