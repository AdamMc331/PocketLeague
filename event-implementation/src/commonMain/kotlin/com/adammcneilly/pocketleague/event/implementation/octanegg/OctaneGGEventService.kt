package com.adammcneilly.pocketleague.event.implementation.octanegg

import com.adammcneilly.pocketleague.core.data.DataResult
import com.adammcneilly.pocketleague.core.models.EventOverview
import com.adammcneilly.pocketleague.core.models.EventSummary
import com.adammcneilly.pocketleague.event.api.EventRepository
import com.adammcneilly.pocketleague.event.implementation.octanegg.dtos.EventDTO
import com.adammcneilly.pocketleague.event.implementation.octanegg.dtos.EventListResponseDTO
import com.adammcneilly.pocketleague.event.implementation.octanegg.mappers.toEventSummary
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * A concrete implementation of an [EventRepository] that requests data from the
 * Octane.gg API.
 */
class OctaneGGEventService : EventRepository {

    private val apiClient = OctaneGGAPIClient()

    override fun fetchEventOverview(eventId: String): Flow<DataResult<EventOverview>> {
        TODO("Not yet implemented")
    }

    override fun fetchEventSummaries(): Flow<DataResult<List<EventSummary>>> {
        return flow {
            val apiResult = apiClient.getResponse<EventListResponseDTO>("events") {
                this.parameter("group", "rlcs2122")
                this.parameter("sortBy", "startDate")
            }

            val mappedResult: DataResult<List<EventSummary>> = when (apiResult) {
                is DataResult.Success -> {
                    val mappedEvents = apiResult.data.events.map(EventDTO::toEventSummary)

                    DataResult.Success(mappedEvents)
                }
                is DataResult.Error -> {
                    DataResult.Error(apiResult.error)
                }
            }

            emit(mappedResult)
        }
    }
}
