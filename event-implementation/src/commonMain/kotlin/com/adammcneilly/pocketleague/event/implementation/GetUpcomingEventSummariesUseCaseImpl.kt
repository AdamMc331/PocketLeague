package com.adammcneilly.pocketleague.event.implementation

import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.event.api.EventRepository
import com.adammcneilly.pocketleague.event.api.GetUpcomingEventSummariesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Concrete implementation of [GetUpcomingEventSummariesUseCase] that will fetch
 * events from the supplied [repository].
 */
class GetUpcomingEventSummariesUseCaseImpl(
    private val repository: EventRepository,
) : GetUpcomingEventSummariesUseCase {

    override fun invoke(leagueSlug: String): Flow<GetUpcomingEventSummariesUseCase.Result> {
        return repository.fetchUpcomingEventSummaries(leagueSlug).map { repositoryResult ->
            when (repositoryResult) {
                is Result.Success -> {
                    GetUpcomingEventSummariesUseCase.Result.Success(repositoryResult.data)
                }
                is Result.Error -> {
                    GetUpcomingEventSummariesUseCase.Result.Error(repositoryResult.error.message)
                }
            }
        }
    }
}
