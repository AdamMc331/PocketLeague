package com.adammcneilly.pocketleague.shared.app.data.octanegg.event

import com.adammcneilly.pocketleague.shared.app.core.models.Event
import com.adammcneilly.pocketleague.shared.app.data.event.EventListRequest
import com.adammcneilly.pocketleague.shared.app.data.event.EventRepository
import com.adammcneilly.pocketleague.shared.app.data.octanegg.dto.OctaneGGEvent
import com.adammcneilly.pocketleague.shared.app.data.octanegg.dto.OctaneGGEventListResponse
import com.adammcneilly.pocketleague.shared.app.data.remote.BaseKtorClient
import com.adammcneilly.pocketleague.shared.app.data.remote.RemoteParams
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * An implementation of [EventRepository] that requests
 * data from the supplied [apiClient], assuming it's an octane.gg client.
 */
class OctaneGGEventRepository(
    private val apiClient: BaseKtorClient,
) : EventRepository {
    override fun getEvents(
        request: EventListRequest,
    ): Flow<List<Event>> {
        return when (request) {
            is EventListRequest.Id -> {
                flow {
                    val eventsResult = apiClient.getResponse<OctaneGGEvent>(
                        endpoint = eventByIdEndpoint(request.eventId),
                        params = getParamsForRequest(request),
                    ).map { octaneEvent ->
                        listOf(octaneEvent.toEvent())
                    }

                    emit(eventsResult.getOrNull().orEmpty())
                }
            }

            else -> {
                flow {
                    val eventsResults = apiClient.getResponse<OctaneGGEventListResponse>(
                        endpoint = EVENTS_ENDPOINT,
                        params = getParamsForRequest(request),
                    ).map { octaneEventList ->
                        octaneEventList.events?.map(OctaneGGEvent::toEvent).orEmpty()
                    }

                    emit(eventsResults.getOrNull().orEmpty())
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
            id: String,
        ): String {
            return "$EVENTS_ENDPOINT/$id"
        }

        private const val AFTER_KEY = "after"
        private const val DATE_KEY = "date"
        private const val GROUP_KEY = "group"
    }
}
