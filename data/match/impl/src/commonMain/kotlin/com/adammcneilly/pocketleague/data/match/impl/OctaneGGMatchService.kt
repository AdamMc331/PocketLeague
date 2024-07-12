package com.adammcneilly.pocketleague.data.match.impl

import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.data.match.api.MatchListRequest
import com.adammcneilly.pocketleague.data.match.api.RemoteMatchService
import com.adammcneilly.pocketleague.data.octanegg.models.OctaneGGMatch
import com.adammcneilly.pocketleague.data.octanegg.models.OctaneGGMatchListResponse
import com.adammcneilly.pocketleague.data.octanegg.models.toMatch
import com.adammcneilly.pocketleague.data.remote.BaseKTORClient
import com.adammcneilly.pocketleague.data.remote.RemoteParams

/**
 * An implementation of [RemoteMatchService] that requests
 * data from the supplied [apiClient].
 */
class OctaneGGMatchService(
    private val apiClient: BaseKTORClient,
) : RemoteMatchService {
    override suspend fun fetch(
        request: MatchListRequest,
    ): Result<List<Match>> {
        val matchId = request.matchId

        return if (matchId != null) {
            requestSingleMatch(matchId)
        } else {
            requestMatchList(request)
        }
    }

    private suspend fun requestMatchList(
        request: MatchListRequest,
    ): Result<List<Match>> {
        return apiClient.getResponse<OctaneGGMatchListResponse>(
            endpoint = MATCHES_ENDPOINT,
            params = getParamsForRequest(request),
        ).map { octaneMatches ->
            octaneMatches.matches?.map(OctaneGGMatch::toMatch).orEmpty()
        }
    }

    private suspend fun requestSingleMatch(
        matchId: Match.Id,
    ): Result<List<Match>> {
        return apiClient.getResponse<OctaneGGMatch>(
            endpoint = matchByIdEndpoint(matchId),
        ).map { octaneMatch ->
            listOf(octaneMatch.toMatch())
        }
    }

    private fun getParamsForRequest(
        request: MatchListRequest,
    ): RemoteParams {
        return mapOf(
            AFTER_KEY to request.startDateUTC,
            BEFORE_KEY to request.endDateUTC,
            TEAM_KEY to request.teamId,
            EVENT_KEY to request.eventId?.id,
            STAGE_KEY to request.stageId?.id,
            GROUP_KEY to "rlcs",
        )
    }

    companion object {
        private const val MATCHES_ENDPOINT = "/matches"

        private fun matchByIdEndpoint(
            id: Match.Id,
        ): String {
            return "$MATCHES_ENDPOINT/${id.id}"
        }

        private const val BEFORE_KEY = "before"
        private const val AFTER_KEY = "after"
        private const val GROUP_KEY = "group"
        private const val EVENT_KEY = "event"
        private const val STAGE_KEY = "stage"
        private const val TEAM_KEY = "team"
    }
}
