package com.adammcneilly.pocketleague.feature.eventsummarylist.domain

import com.adammcneilly.pocketleague.core.data.DataResult
import com.adammcneilly.pocketleague.event.api.EventRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Concrete implementation of [GetEventSummariesUseCase] that will fetch
 * events from the supplied [repository].
 */
class GetEventSummariesUseCaseImpl(
    private val repository: EventRepository,
) : GetEventSummariesUseCase {

    override fun invoke(
        request: GetEventSummariesUseCase.Request,
    ): Flow<GetEventSummariesUseCase.Result> {

        return repository.fetchEventSummaries().map { repositoryResult ->
            when (repositoryResult) {
                is DataResult.Success -> {
                    GetEventSummariesUseCase.Result.Success(repositoryResult.data)
                }
                is DataResult.Error -> {
                    GetEventSummariesUseCase.Result.Error(repositoryResult.error.message)
                }
            }
        }
    }
}
