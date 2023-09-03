package com.adammcneilly.pocketleague.data.match

import com.adammcneilly.pocketleague.core.models.Match
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.onEach
import org.mobilenativefoundation.store.store5.Fetcher
import org.mobilenativefoundation.store.store5.SourceOfTruth
import org.mobilenativefoundation.store.store5.StoreBuilder
import org.mobilenativefoundation.store.store5.StoreReadRequest

/**
 * A repository class for matches that uses the Store library.
 */
class StoreMatchRepository(
    private val matchFetcher: MatchFetcher,
    private val matchSourceOfTruth: MatchSourceOfTruth,
) {
    private val store = StoreBuilder.from<MatchListRequest, Result<List<Match>>, List<Match>>(
        fetcher = Fetcher.of { request ->
            matchFetcher.fetch(request)
        },
        sourceOfTruth = SourceOfTruth.Companion.of(
            reader = { request ->
                matchSourceOfTruth.stream(request)
            },
            writer = { _, matchResult ->
                val matches = matchResult.getOrNull().orEmpty()
                matchSourceOfTruth.insertMatches(matches)
            },
        ),
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
        ).onEach { response ->
            println("ADAMLOG - Response: $response")
        }.mapNotNull { storeResponse ->
            // Still need to handle all types?
            storeResponse.dataOrNull()
        }
    }
}
