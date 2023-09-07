package com.adammcneilly.pocketleague.data.match.impl

import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.data.match.api.LocalMatchService
import com.adammcneilly.pocketleague.data.match.api.MatchListRequest
import com.adammcneilly.pocketleague.data.match.api.MatchRepository
import com.adammcneilly.pocketleague.data.match.api.RemoteMatchService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import org.mobilenativefoundation.store.store5.Fetcher
import org.mobilenativefoundation.store.store5.SourceOfTruth
import org.mobilenativefoundation.store.store5.StoreBuilder
import org.mobilenativefoundation.store.store5.StoreReadRequest
import org.mobilenativefoundation.store.store5.StoreReadResponse

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
                        // we could just show an empty match list, BUT we should log something
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
