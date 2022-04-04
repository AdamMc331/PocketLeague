package com.adammcneilly.pocketleague.shared.phasedetail.domain

import com.adammcneilly.pocketleague.shared.core.models.PhaseDetail
import kotlinx.coroutines.flow.Flow

/**
 * A use case to request detailed information about a phase given its ID.
 */
interface GetPhaseDetailUseCase {

    /**
     * @see [GetPhaseDetailUseCase]
     */
    suspend operator fun invoke(phaseId: String): Flow<Result>

    /**
     * A collection of possible return values for a [GetPhaseDetailUseCase].
     */
    sealed class Result {
        /**
         * This will be returned when we were able to successfully retrieve a [phaseDetail].
         */
        data class Success(
            val phaseDetail: PhaseDetail,
        ) : Result()

        /**
         * This will be returned whenever we have an error requesting a phase detail, and the
         * supplied [errorMessage] will tell us what went wrong.
         */
        data class Error(
            val errorMessage: String?,
        ) : Result()
    }
}
