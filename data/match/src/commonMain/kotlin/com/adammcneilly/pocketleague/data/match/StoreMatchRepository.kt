package com.adammcneilly.pocketleague.data.match

import com.adammcneilly.pocketleague.core.models.Match
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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
    private val remoteMatchFetcher: RemoteMatchFetcher,
    private val localMatchService: LocalMatchService,
) {
    private val store = StoreBuilder.from<MatchListRequest, Result<List<Match>>, List<Match>>(
        fetcher = Fetcher.of { request ->
            remoteMatchFetcher.fetch(request)
        },
        sourceOfTruth = SourceOfTruth.Companion.of(
            reader = { req ->
                when (req) {
                    is MatchListRequest.DateRange -> {
                        localMatchService.getMatchesInDateRange(
                            startDateUTC = req.startDateUTC,
                            endDateUTC = req.endDateUTC,
                        )
                    }
                    is MatchListRequest.EventStage -> {
                        localMatchService.getMatchesForEventStage(
                            eventId = req.eventId,
                            stageId = req.stageId,
                        )
                    }
                    is MatchListRequest.Id -> {
                        localMatchService.getMatchDetail(req.matchId).map { match ->
                            listOf(match)
                        }
                    }
                }
            },
            writer = { req, matchResult ->
                val matches = matchResult.getOrNull().orEmpty()
                localMatchService.insertMatches(matches)
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
