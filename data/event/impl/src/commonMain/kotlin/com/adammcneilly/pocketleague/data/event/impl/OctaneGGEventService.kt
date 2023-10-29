package com.adammcneilly.pocketleague.data.event.impl

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.data.event.api.EventListRequest
import com.adammcneilly.pocketleague.data.event.api.RemoteEventService
import com.adammcneilly.pocketleague.data.octanegg.models.OctaneGGEvent
import com.adammcneilly.pocketleague.data.octanegg.models.OctaneGGEventListResponse
import com.adammcneilly.pocketleague.data.octanegg.models.toEvent
import com.adammcneilly.pocketleague.data.remote.BaseKTORClient
import com.adammcneilly.pocketleague.data.remote.RemoteParams

/**
 * An implementation of [RemoteEventService] that requests
 * data from the supplied [apiClient].
 */
class OctaneGGEventService(
    private val apiClient: BaseKTORClient,
) : RemoteEventService {
    override suspend fun fetch(
        request: EventListRequest,
    ): Result<List<Event>> {
        return when (request) {
            is EventListRequest.Id -> {
                apiClient.getResponse<OctaneGGEvent>(
                    endpoint = eventByIdEndpoint(request.eventId),
                ).map { octaneEvent ->
                    listOf(octaneEvent.toEvent())
                }
            }
            else -> {
                apiClient.getResponse<OctaneGGEventListResponse>(
                    endpoint = EVENTS_ENDPOINT,
                    params = getParamsForRequest(request),
                ).map { octaneEventList ->
                    octaneEventList.events?.map(OctaneGGEvent::toEvent).orEmpty()
                }
            }
        }
    }

    private fun getParamsForRequest(
        request: EventListRequest,
    ): RemoteParams {
        val initialParams = when (request) {
            is EventListRequest.AfterDate -> {
                mapOf(
                    AFTER_KEY to request.dateUtc,
                )
            }
            is EventListRequest.OnDate -> {
                mapOf(
                    DATE_KEY to request.dateUtc,
                )
            }
            is EventListRequest.Id -> {
                emptyMap()
            }
        }

        return initialParams + mapOf(
            GROUP_KEY to "rlcs",
        )
    }

    companion object {
        private const val EVENTS_ENDPOINT = "/events"

        private fun eventByIdEndpoint(
            id: Event.Id,
        ): String {
            return "$EVENTS_ENDPOINT/${id.id}"
        }

        private const val AFTER_KEY = "after"
        private const val DATE_KEY = "date"
        private const val GROUP_KEY = "group"
    }
}
