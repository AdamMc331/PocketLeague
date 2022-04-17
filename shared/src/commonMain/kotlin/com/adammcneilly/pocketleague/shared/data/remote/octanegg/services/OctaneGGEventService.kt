package com.adammcneilly.pocketleague.shared.data.remote.octanegg.services

import com.adammcneilly.pocketleague.shared.data.DataResult
import com.adammcneilly.pocketleague.shared.data.models.EventListRequest
import com.adammcneilly.pocketleague.shared.data.remote.octanegg.OctaneGGAPIClient
import com.adammcneilly.pocketleague.shared.data.remote.octanegg.OctaneGGEndpoints
import com.adammcneilly.pocketleague.shared.data.remote.octanegg.mappers.toEvent
import com.adammcneilly.pocketleague.shared.data.remote.octanegg.models.OctaneGGEvent
import com.adammcneilly.pocketleague.shared.data.remote.octanegg.models.OctaneGGEventListResponse
import com.adammcneilly.pocketleague.shared.data.repositories.EventRepository
import com.adammcneilly.pocketleague.shared.datetime.DateTimeFormatter
import com.adammcneilly.pocketleague.shared.models.Event
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * A concrete implementation of an [EventRepository] that will request data using the supplied [apiClient].
 */
class OctaneGGEventService(
    private val apiClient: OctaneGGAPIClient,
) : EventRepository {

    override fun fetchEvents(
        request: EventListRequest,
    ): Flow<DataResult<List<Event>>> {
        return flow {
            val apiResult = apiClient.getResponse<OctaneGGEventListResponse>(
                endpoint = OctaneGGEndpoints.EVENTS,
                requestBuilder = {
                    addEventParameters(request)
                },
            )

            val mappedResult = when (apiResult) {
                is DataResult.Success -> {
                    val mappedEvents = apiResult.data.events?.map(OctaneGGEvent::toEvent).orEmpty()

                    val sortedEvents = mappedEvents.sortedBy(Event::startDate)

                    DataResult.Success(sortedEvents)
                }
                is DataResult.Error -> {
                    DataResult.Error(apiResult.error)
                }
            }

            emit(mappedResult)
        }
    }
}

private fun HttpRequestBuilder.addEventParameters(request: EventListRequest) {
    val dateTimeFormatter = DateTimeFormatter()
    val octaneGGDateFormat = "yyyy-MM-dd"

    if (request.group != null) {
        this.parameter("group", request.group)
    }

    if (request.tiers != null) {
        this.parameter("tier", request.tiers)
    }

    if (request.after != null) {
        val afterString = dateTimeFormatter.formatLocalDateTime(request.after, octaneGGDateFormat)
        this.parameter("after", afterString)
    }

    if (request.before != null) {
        val beforeString = dateTimeFormatter.formatLocalDateTime(request.before, octaneGGDateFormat)
        this.parameter("before", beforeString)
    }
}
