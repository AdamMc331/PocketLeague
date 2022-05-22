package com.adammcneilly.pocketleague.core.data.remote.octanegg.services

import com.adammcneilly.pocketleague.core.data.DataState
import com.adammcneilly.pocketleague.core.data.models.EventListRequest
import com.adammcneilly.pocketleague.core.data.remote.octanegg.OctaneGGAPIClient
import com.adammcneilly.pocketleague.core.data.remote.octanegg.OctaneGGEndpoints
import com.adammcneilly.pocketleague.core.data.remote.octanegg.mappers.toEvent
import com.adammcneilly.pocketleague.core.data.remote.octanegg.models.OctaneGGEvent
import com.adammcneilly.pocketleague.core.data.remote.octanegg.models.OctaneGGEventListResponse
import com.adammcneilly.pocketleague.core.data.repositories.EventRepository
import com.adammcneilly.pocketleague.core.datetime.DateTimeFormatter
import com.adammcneilly.pocketleague.core.models.Event
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * A concrete implementation of an [EventRepository] that will request data using the supplied [apiClient].
 */
class OctaneGGEventService(
    private val apiClient: OctaneGGAPIClient = OctaneGGAPIClient(),
) : EventRepository {

    override fun fetchEvents(
        request: EventListRequest,
    ): Flow<DataState<List<Event>>> {
        return flow {
            val apiResult = apiClient.getResponse<OctaneGGEventListResponse>(
                endpoint = OctaneGGEndpoints.EVENTS,
                requestBuilder = {
                    addEventParameters(request)
                },
            )

            val mappedResult = when (apiResult) {
                is DataState.Loading -> DataState.Loading
                is DataState.Success -> {
                    val mappedEvents = apiResult.data.events?.map(OctaneGGEvent::toEvent).orEmpty()

                    val sortedEvents = mappedEvents.sortedBy(Event::startDate)

                    DataState.Success(sortedEvents)
                }
                is DataState.Error -> {
                    DataState.Error(apiResult.error)
                }
            }

            emit(mappedResult)
        }
    }

    override fun fetchEvent(eventId: String): Flow<DataState<Event>> {
        return flow {
            val apiResult = apiClient.getResponse<OctaneGGEvent>(
                endpoint = OctaneGGEndpoints.EVENTS + "/$eventId",
            )

            val mappedResult = when (apiResult) {
                is DataState.Loading -> DataState.Loading
                is DataState.Success -> {
                    val mappedEvent = apiResult.data?.let(OctaneGGEvent::toEvent)

                    DataState.Success(mappedEvent)
                }
                is DataState.Error -> {
                    DataState.Error(apiResult.error)
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
