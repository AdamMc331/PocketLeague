package com.adammcneilly.pocketleague.event.implementation

import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.event.api.EventListRequestBody
import com.adammcneilly.pocketleague.event.api.EventRepository
import com.adammcneilly.pocketleague.event.api.GetEventSummariesUseCase
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
        leagueSlug: String,
        request: GetEventSummariesUseCase.Request,
    ): Flow<GetEventSummariesUseCase.Result> {
        val requestBody = request.toDataLayerRequest()

        return repository.fetchEventSummaries(leagueSlug, requestBody).map { repositoryResult ->
            when (repositoryResult) {
                is Result.Success -> {
                    GetEventSummariesUseCase.Result.Success(repositoryResult.data)
                }
                is Result.Error -> {
                    GetEventSummariesUseCase.Result.Error(repositoryResult.error.message)
                }
            }
        }
    }
}

private fun GetEventSummariesUseCase.Request.toDataLayerRequest(): EventListRequestBody {
    return EventListRequestBody(
        upcoming = this.upcoming,
    )
}
