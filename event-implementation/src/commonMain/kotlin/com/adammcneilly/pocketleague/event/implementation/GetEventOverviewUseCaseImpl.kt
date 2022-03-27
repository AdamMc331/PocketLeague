package com.adammcneilly.pocketleague.event.implementation

import com.adammcneilly.pocketleague.shared.data.Result
import com.adammcneilly.pocketleague.shared.data.event.EventRepository
import com.adammcneilly.pocketleague.shared.eventoverview.domain.GetEventOverviewUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * A concrete implementation of [GetEventOverviewUseCase] that will request information
 * from the supplied [repository].
 */
class GetEventOverviewUseCaseImpl(
    private val repository: EventRepository,
) : GetEventOverviewUseCase {

    override fun invoke(eventId: String): Flow<GetEventOverviewUseCase.Result> {
        return repository.fetchEventOverview(eventId).map { repoResult ->
            when (repoResult) {
                is Result.Success -> {
                    GetEventOverviewUseCase.Result.Success(repoResult.data)
                }
                is Result.Error -> {
                    GetEventOverviewUseCase.Result.Error(repoResult.error.message)
                }
            }
        }
    }
}
