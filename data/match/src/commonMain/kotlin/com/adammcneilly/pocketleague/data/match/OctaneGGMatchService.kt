package com.adammcneilly.pocketleague.data.match

import com.adammcneilly.pocketleague.core.data.DataState
import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.data.octanegg.OctaneGGAPIClient
import com.adammcneilly.pocketleague.data.octanegg.models.OctaneGGMatch
import com.adammcneilly.pocketleague.data.octanegg.models.OctaneGGMatchListResponse
import com.adammcneilly.pocketleague.data.octanegg.models.toMatch
import com.adammcneilly.pocketleague.data.remote.BaseKTORClient
import com.adammcneilly.pocketleague.data.remote.RemoteParams

/**
 * A concrete implementation of a [MatchService] that will request data using
 * the supplied [apiClient].
 */
internal class OctaneGGMatchService(
    private val apiClient: BaseKTORClient,
) : MatchService {

    constructor() : this(OctaneGGAPIClient)

    override suspend fun fetchMatchDetail(matchId: String): DataState<Match> {
        return apiClient.getResponse<OctaneGGMatch>(
            endpoint = "$MATCHES_ENDPOINT/$matchId",
        ).map { octaneMatch ->
            octaneMatch.toMatch()
        }
    }

    override suspend fun fetchMatches(request: MatchListRequest): DataState<List<Match>> {
        return apiClient.getResponse<OctaneGGMatchListResponse>(
            endpoint = MATCHES_ENDPOINT,
            params = request.toOctaneParams(),
        ).map { octaneMatchListResponse ->
            val mappedMatches = octaneMatchListResponse.matches?.map(OctaneGGMatch::toMatch).orEmpty()

            val sortedMatches = mappedMatches.sortedByDescending(Match::dateUTC)

            sortedMatches
        }
    }

    companion object {
        private const val MATCHES_ENDPOINT = "/matches"
    }
}

private fun MatchListRequest.toOctaneParams(): RemoteParams {
    return mapOf(
        "after" to this.after.toString(),
        "before" to this.before.toString(),
        "group" to this.group,
        "event" to this.eventId,
        "stage" to this.stageId,
        "region" to this.region,
    )
}
