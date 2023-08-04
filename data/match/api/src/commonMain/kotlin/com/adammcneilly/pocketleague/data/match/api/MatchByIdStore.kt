package com.adammcneilly.pocketleague.data.match.api

import com.adammcneilly.pocketleague.core.models.Match
import org.mobilenativefoundation.store.store5.Fetcher
import org.mobilenativefoundation.store.store5.FetcherResult
import org.mobilenativefoundation.store.store5.SourceOfTruth
import org.mobilenativefoundation.store.store5.StoreBuilder

class MatchByIdStore(
    private val localMatchService: LocalMatchService,
    private val remoteMatchService: RemoteMatchService,
) {
    val store = StoreBuilder.from(
        fetcher = Fetcher.ofResult<Match.Id, Match> { matchId ->
            remoteMatchService.fetchMatch(matchId).fold(
                onSuccess = {
                    FetcherResult.Data(it)
                },
                onFailure = {
                    FetcherResult.Error.Exception(it)
                },
            )
        },
        sourceOfTruth = SourceOfTruth.Companion.of(
            reader = { matchId ->
                localMatchService.observeMatch(matchId)
            },
            writer = { _, match ->
                localMatchService.insertMatches(listOf(match))
            },
        ),
    )
}
