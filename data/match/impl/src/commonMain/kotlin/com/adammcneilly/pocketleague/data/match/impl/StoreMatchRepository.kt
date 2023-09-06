package com.adammcneilly.pocketleague.data.match.impl

import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.data.match.api.LocalMatchService
import com.adammcneilly.pocketleague.data.match.api.MatchListRequest
import com.adammcneilly.pocketleague.data.match.api.MatchRepository
import com.adammcneilly.pocketleague.data.match.api.RemoteMatchService
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
    private val remoteMatchService: RemoteMatchService,
    private val localMatchService: LocalMatchService,
) : MatchRepository {
    private val store = StoreBuilder.from<MatchListRequest, Result<List<Match>>, List<Match>>(
        fetcher = Fetcher.of { request ->
            remoteMatchService.fetch(request)
        },
        sourceOfTruth = SourceOfTruth.Companion.of(
            reader = { request ->
                localMatchService.stream(request)
            },
            writer = { _, matchResult ->
                val matches = matchResult.getOrNull().orEmpty()
                localMatchService.insertMatches(matches)
            },
        ),
    ).build()

    override fun stream(
        request: MatchListRequest,
        refreshCache: Boolean,
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
            storeResponse.dataOrNull().orEmpty()
        }
    }
}
