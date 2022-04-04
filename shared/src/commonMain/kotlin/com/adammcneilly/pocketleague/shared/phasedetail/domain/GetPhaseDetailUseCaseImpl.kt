package com.adammcneilly.pocketleague.shared.phasedetail.domain

import com.adammcneilly.pocketleague.shared.data.Result
import com.adammcneilly.pocketleague.shared.data.phase.PhaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * A concrete implementation of a [GetPhaseDetailUseCase] that requests the necessary information
 * from the supplied [repository].
 */
class GetPhaseDetailUseCaseImpl(
    private val repository: PhaseRepository,
) : GetPhaseDetailUseCase {

    override suspend fun invoke(phaseId: String): Flow<GetPhaseDetailUseCase.Result> {
        return repository.fetchPhaseDetail(phaseId).map { repoResult ->
            when (repoResult) {
                is Result.Success -> {
                    GetPhaseDetailUseCase.Result.Success(
                        phaseDetail = repoResult.data,
                    )
                }
                is Result.Error -> {
                    GetPhaseDetailUseCase.Result.Error(
                        errorMessage = repoResult.error.message,
                    )
                }
            }
        }
    }
}
