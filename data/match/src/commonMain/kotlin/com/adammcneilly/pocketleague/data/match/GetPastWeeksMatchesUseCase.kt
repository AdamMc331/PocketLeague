package com.adammcneilly.pocketleague.data.match

import com.adammcneilly.pocketleague.core.datetime.TimeProvider
import com.adammcneilly.pocketleague.core.models.Match
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import org.mobilenativefoundation.store.store5.Fetcher
import org.mobilenativefoundation.store.store5.SourceOfTruth
import org.mobilenativefoundation.store.store5.StoreBuilder
import org.mobilenativefoundation.store.store5.StoreReadRequest

private const val DAYS_PER_WEEK = 7

/**
 * Return an observable type of matches for the past week.
 */
class GetPastWeeksMatchesUseCase(
    private val timeProvider: TimeProvider,
    private val remoteMatchService: RemoteMatchService,
    private val localMatchService: LocalMatchService,
) {
    private val store = StoreBuilder.from(
        fetcher = Fetcher.of {
            remoteMatchService.getMatchesInDateRange(
                startDateUTC = timeProvider.daysAgo(DAYS_PER_WEEK),
                endDateUTC = timeProvider.now(),
            )
        },
        sourceOfTruth = SourceOfTruth.Companion.of(
            reader = {
                localMatchService.getMatchesInDateRange(
                    startDateUTC = timeProvider.daysAgo(DAYS_PER_WEEK),
                    endDateUTC = timeProvider.now(),
                )
            },
            writer = { _, matchesResult ->
                matchesResult.fold(
                    onSuccess = { matches ->
                        localMatchService.insertMatches(matches)
                    },
                    onFailure = { error ->
                        println("Error occurred fetching matchesResult: $error")
                    },
                )
            },
        ),
    ).build()

    fun invoke(
        refresh: Boolean = true,
    ): Flow<List<Match>> {
        return store.stream(StoreReadRequest.cached(Unit, refresh))
            .mapNotNull { storeResponse ->
                storeResponse.dataOrNull()
            }
    }
}
