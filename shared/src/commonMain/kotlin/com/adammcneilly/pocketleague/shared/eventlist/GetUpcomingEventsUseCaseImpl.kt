package com.adammcneilly.pocketleague.shared.eventlist

import com.adammcneilly.pocketleague.shared.data.DataResult
import com.adammcneilly.pocketleague.shared.data.models.EventListRequest
import com.adammcneilly.pocketleague.shared.data.repositories.EventRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

/**
 * A concrete implementation of [GetUpcomingEventsUseCase] that will return information from
 * the supplied [repository].
 */
class GetUpcomingEventsUseCaseImpl(
    private val repository: EventRepository,
) : GetUpcomingEventsUseCase {

    override fun invoke(): Flow<GetUpcomingEventsUseCase.Result> {
        val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

        val request = EventListRequest(
            after = today,
        )

        return repository.fetchEvents(request).map { repoResult ->
            when (repoResult) {
                is DataResult.Success -> {
                    GetUpcomingEventsUseCase.Result.Success(repoResult.data)
                }
                is DataResult.Error -> {
                    GetUpcomingEventsUseCase.Result.Error(repoResult.error)
                }
            }
        }
    }
}
