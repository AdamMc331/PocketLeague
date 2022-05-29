package com.adammcneilly.pocketleague.core.data.remote.octanegg.services

import com.adammcneilly.pocketleague.core.data.DataState
import com.adammcneilly.pocketleague.core.data.models.EventListRequest
import com.adammcneilly.pocketleague.core.data.remote.octanegg.OctaneGGAPIClient
import com.adammcneilly.pocketleague.core.data.remote.octanegg.OctaneGGEndpoints
import com.adammcneilly.pocketleague.core.data.remote.octanegg.mappers.toEvent
import com.adammcneilly.pocketleague.core.data.remote.octanegg.mappers.toTeam
import com.adammcneilly.pocketleague.core.data.remote.octanegg.models.OctaneGGEvent
import com.adammcneilly.pocketleague.core.data.remote.octanegg.models.OctaneGGEventListResponse
import com.adammcneilly.pocketleague.core.data.remote.octanegg.models.OctaneGGEventParticipants
import com.adammcneilly.pocketleague.core.data.repositories.EventRepository
import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.Team
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
            emit(DataState.Loading)

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

                    val sortedEvents = mappedEvents.sortedBy(Event::startDateUTC)

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
                    val mappedEvent = apiResult.data.let(OctaneGGEvent::toEvent)

                    DataState.Success(mappedEvent)
                }
                is DataState.Error -> {
                    DataState.Error(apiResult.error)
                }
            }

            emit(mappedResult)
        }
    }

    override fun fetchEventParticipants(eventId: String): Flow<DataState<List<Team>>> {
        return flow {
            emit(DataState.Loading)

            val apiResult = apiClient.getResponse<OctaneGGEventParticipants>(
                endpoint = OctaneGGEndpoints.eventParticipantsEndpoint(eventId)
            )

            val mappedResult = when (apiResult) {
                is DataState.Loading -> {
                    DataState.Loading
                }
                is DataState.Success -> {
                    val mappedTeams = apiResult.data.participants.mapNotNull {
                        it.team?.toTeam()
                    }

                    DataState.Success(mappedTeams)
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
    if (request.group != null) {
        this.parameter("group", request.group)
    }

    if (request.tiers != null) {
        this.parameter("tier", request.tiers)
    }

    if (request.after != null) {
        this.parameter("after", request.after.toString())
    }

    if (request.before != null) {
        this.parameter("before", request.before.toString())
    }

    if (request.date != null) {
        this.parameter("date", request.date.toString())
    }
}
