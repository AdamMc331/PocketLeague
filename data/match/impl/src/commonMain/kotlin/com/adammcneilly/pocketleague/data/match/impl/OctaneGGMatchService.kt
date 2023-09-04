package com.adammcneilly.pocketleague.data.match.impl

import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.data.match.api.MatchListRequest
import com.adammcneilly.pocketleague.data.octanegg.models.OctaneGGMatch
import com.adammcneilly.pocketleague.data.octanegg.models.OctaneGGMatchListResponse
import com.adammcneilly.pocketleague.data.octanegg.models.toMatch
import com.adammcneilly.pocketleague.data.remote.BaseKTORClient

/**
 * An implementation of [RemoteMatchService] that requests
 * data from the supplied [apiClient].
 */
class OctaneGGMatchService(
    private val apiClient: BaseKTORClient,
) : RemoteMatchService {

    override suspend fun fetch(request: MatchListRequest): Result<List<Match>> {
        return when (request) {
            is MatchListRequest.Id -> {
                apiClient.getResponse<OctaneGGMatch>(
                    endpoint = matchByIdEndpoint(request.matchId),
                ).map { octaneMatch ->
                    listOf(octaneMatch.toMatch())
                }
            }

            else -> {
                apiClient.getResponse<OctaneGGMatchListResponse>(
                    endpoint = MATCHES_ENDPOINT,
                    params = getParamsForRequest(request),
                ).map { octaneMatchList ->
                    octaneMatchList.matches?.map(OctaneGGMatch::toMatch).orEmpty()
                }
            }
        }
    }

    private fun getParamsForRequest(
        request: MatchListRequest,
    ): Map<String, String> {
        val initialParams = when (request) {
            is MatchListRequest.DateRange -> {
                mapOf(
                    AFTER_KEY to request.startDateUTC,
                    BEFORE_KEY to request.endDateUTC,
                )
            }

            is MatchListRequest.EventStage -> {
                mapOf(
                    EVENT_KEY to request.eventId.id,
                    STAGE_KEY to request.stageId.id,
                )
            }

            is MatchListRequest.Id -> {
                emptyMap()
            }
        }

        return initialParams + mapOf(
            GROUP_KEY to "rlcs",
        )
    }

    companion object {
        private const val MATCHES_ENDPOINT = "/matches"

        private fun matchByIdEndpoint(id: Match.Id): String {
            return "$MATCHES_ENDPOINT/${id.id}"
        }

        private const val BEFORE_KEY = "before"
        private const val AFTER_KEY = "after"
        private const val GROUP_KEY = "group"
        private const val EVENT_KEY = "event"
        private const val STAGE_KEY = "stage"
    }
}
