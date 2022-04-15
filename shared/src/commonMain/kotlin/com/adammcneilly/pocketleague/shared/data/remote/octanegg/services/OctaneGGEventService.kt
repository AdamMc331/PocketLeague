package com.adammcneilly.pocketleague.shared.data.remote.octanegg.services

import com.adammcneilly.pocketleague.shared.data.DataResult
import com.adammcneilly.pocketleague.shared.data.remote.octanegg.OctaneGGAPIClient
import com.adammcneilly.pocketleague.shared.data.remote.octanegg.OctaneGGEndpoints
import com.adammcneilly.pocketleague.shared.data.remote.octanegg.mappers.toEvent
import com.adammcneilly.pocketleague.shared.data.remote.octanegg.models.OctaneGGEvent
import com.adammcneilly.pocketleague.shared.data.remote.octanegg.models.OctaneGGEventListResponse
import com.adammcneilly.pocketleague.shared.data.repositories.EventRepository
import com.adammcneilly.pocketleague.shared.models.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * A concrete implementation of an [EventRepository] that will request data using the supplied [apiClient].
 */
class OctaneGGEventService(
    private val apiClient: OctaneGGAPIClient,
) : EventRepository {

    override fun fetchEvents(): Flow<DataResult<List<Event>>> {
        return flow {
            val apiResult = apiClient.getResponse<OctaneGGEventListResponse>(
                endpoint = OctaneGGEndpoints.EVENTS,
            )

            val mappedResult = when (apiResult) {
                is DataResult.Success -> {
                    val mappedEvents = apiResult.data.events?.map(OctaneGGEvent::toEvent).orEmpty()
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
