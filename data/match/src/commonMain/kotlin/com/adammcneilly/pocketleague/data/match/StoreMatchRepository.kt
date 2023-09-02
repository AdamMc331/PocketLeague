package com.adammcneilly.pocketleague.data.match

import com.adammcneilly.pocketleague.core.models.Match
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import org.mobilenativefoundation.store.store5.Fetcher
import org.mobilenativefoundation.store.store5.StoreBuilder
import org.mobilenativefoundation.store.store5.StoreReadRequest

/**
 * A repository class for matches that uses the Store library.
 */
class StoreMatchRepository(
    private val remoteMatchFetcher: RemoteMatchFetcher,
    private val localMatchService: LocalMatchService,
) {
    private val store = StoreBuilder.from(
        fetcher = Fetcher.of<MatchListRequest, Result<List<Match>>> { request ->
            remoteMatchFetcher.fetch(request)
        },
    ).build()

    /**
     * Provide a flow response of matches for the given [request].
     *
     * All data is provided from our source of truth, and refreshed unless
     * [refreshCache] is set to false. This is ideal for stale data that is unlikely to
     * have changed.
     */
    fun stream(
        request: MatchListRequest,
        refreshCache: Boolean = true,
    ): Flow<List<Match>> {
        return store.stream(
            request = StoreReadRequest.cached(
                key = request,
                refresh = refreshCache,
            ),
        ).mapNotNull { storeResponse ->
            // Still need to handle all types?
            storeResponse.dataOrNull()?.getOrNull()
        }
    }
}
