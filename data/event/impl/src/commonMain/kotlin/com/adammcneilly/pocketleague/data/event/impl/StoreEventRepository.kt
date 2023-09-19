package com.adammcneilly.pocketleague.data.event.impl

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.data.event.api.EventListRequest
import com.adammcneilly.pocketleague.data.event.api.EventRepository
import com.adammcneilly.pocketleague.data.event.api.LocalEventService
import com.adammcneilly.pocketleague.data.event.api.RemoteEventService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import org.mobilenativefoundation.store.store5.Fetcher
import org.mobilenativefoundation.store.store5.SourceOfTruth
import org.mobilenativefoundation.store.store5.StoreBuilder
import org.mobilenativefoundation.store.store5.StoreReadRequest
import org.mobilenativefoundation.store.store5.StoreReadResponse

/**
 * A repository class for events that uses the Store library.
 */
class StoreEventRepository(
    private val remoteEventService: RemoteEventService,
    private val localEventService: LocalEventService,
) : EventRepository {
    private val store = StoreBuilder.from<EventListRequest, Result<List<Event>>, List<Event>>(
        fetcher = Fetcher.of { request ->
            remoteEventService.fetch(request)
        },
        sourceOfTruth = SourceOfTruth.Companion.of(
            reader = { request ->
                localEventService.stream(request)
            },
            writer = { _, eventResult ->
                val events = eventResult.getOrNull().orEmpty()
                localEventService.insert(events)
            },
        ),
    ).build()

    override fun stream(
        request: EventListRequest,
        refreshCache: Boolean,
    ): Flow<List<Event>> {
        return store.stream(
            request = StoreReadRequest.cached(
                key = request,
                refresh = refreshCache,
            ),
        )
            .distinctUntilChanged()
            .map { storeResponse ->
                when (storeResponse) {
                    is StoreReadResponse.Data -> {
                        storeResponse.value
                    }

                    is StoreReadResponse.NoNewData -> {
                        emptyList()
                    }

                    is StoreReadResponse.Error.Exception,
                    is StoreReadResponse.Error.Message,
                    -> {
                        // If an error happens, should we surface that?
                        // If an error happens syncing remote data
                        // we could just show an empty Event list, BUT we should log something
                        // (Firebase, Crashlytics, etc) that this error occurred.
                        emptyList()
                    }

                    is StoreReadResponse.Loading -> {
                        // Ideally we return some indicator here that loading happened.
                        // Loading is more interesting, because we can consider what "loading" means.
                        // First app open -> we are actually loading the data to show you.
                        // Next app open -> we have data to show you, but we could be refreshing it.
                        emptyList()
                    }
                }
            }
    }
}
