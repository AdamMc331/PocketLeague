package com.adammcneilly.pocketleague.shared.app.data.octanegg.match

import com.adammcneilly.pocketleague.shared.app.core.models.Match
import com.adammcneilly.pocketleague.shared.app.data.match.MatchListRequest
import com.adammcneilly.pocketleague.shared.app.data.match.MatchRepository
import com.adammcneilly.pocketleague.shared.app.data.octanegg.dto.OctaneGGMatch
import com.adammcneilly.pocketleague.shared.app.data.octanegg.dto.OctaneGGMatchListResponse
import com.adammcneilly.pocketleague.shared.app.data.remote.BaseKtorClient
import com.adammcneilly.pocketleague.shared.app.data.remote.RemoteParams
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * An implementation of [MatchRepository] that requests
 * data from the supplied [apiClient] assuming it's an octane.gg client.
 */
class OctaneGGMatchRepository(
    private val apiClient: BaseKtorClient,
) : MatchRepository {
    override fun getMatches(
        request: MatchListRequest,
    ): Flow<List<Match>> {
        return when (request) {
            is MatchListRequest.Id -> {
                flow {
                    val matchResult = apiClient.getResponse<OctaneGGMatch>(
                        endpoint = matchByIdEndpoint(request.matchId),
                        params = getParamsForRequest(request),
                    ).map { octaneMatch ->
                        listOf(octaneMatch.toMatch())
                    }

                    emit(matchResult.getOrNull().orEmpty())
                }
            }

            else -> {
                flow {
                    val matchResult = apiClient.getResponse<OctaneGGMatchListResponse>(
                        endpoint = MATCHES_ENDPOINT,
                        params = getParamsForRequest(request),
                    ).map { octaneMatchList ->
                        octaneMatchList.matches?.map(OctaneGGMatch::toMatch).orEmpty()
                    }

                    emit(matchResult.getOrNull().orEmpty())
                }
            }
        }
    }

    private fun getParamsForRequest(
        request: MatchListRequest,
    ): RemoteParams {
        val initialParams = when (request) {
            is MatchListRequest.DateRange -> {
                mapOf(
                    AFTER_KEY to request.startDateUTC,
                    BEFORE_KEY to request.endDateUTC,
                )
            }

            is MatchListRequest.EventStage -> {
                mapOf(
                    EVENT_KEY to request.eventId,
                    STAGE_KEY to request.stageId,
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

        private fun matchByIdEndpoint(
            id: String,
        ): String {
            return "$MATCHES_ENDPOINT/$id"
        }

        private const val BEFORE_KEY = "before"
        private const val AFTER_KEY = "after"
        private const val GROUP_KEY = "group"
        private const val EVENT_KEY = "event"
        private const val STAGE_KEY = "stage"
    }
}
