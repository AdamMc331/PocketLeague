package com.adammcneilly.pocketleague.core.data.remote.octanegg.services

import com.adammcneilly.pocketleague.core.data.DataState
import com.adammcneilly.pocketleague.core.data.models.MatchListRequest
import com.adammcneilly.pocketleague.core.data.remote.octanegg.OctaneGGAPIClient
import com.adammcneilly.pocketleague.core.data.remote.octanegg.OctaneGGEndpoints
import com.adammcneilly.pocketleague.core.data.remote.octanegg.mappers.toMatch
import com.adammcneilly.pocketleague.core.data.remote.octanegg.models.OctaneGGMatch
import com.adammcneilly.pocketleague.core.data.remote.octanegg.models.OctaneGGMatchListResponse
import com.adammcneilly.pocketleague.core.data.repositories.MatchRepository
import com.adammcneilly.pocketleague.core.datetime.DateTimeFormatter
import com.adammcneilly.pocketleague.core.models.Match
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * A concrete implementation of a [MatchRepository] that will request data using
 * the supplied [apiClient].
 */
class OctaneGGMatchService(
    private val apiClient: OctaneGGAPIClient = OctaneGGAPIClient(),
) : MatchRepository {

    override fun fetchMatchDetail(matchId: String): Flow<DataState<Match>> {
        return flow {
            emit(DataState.Loading)

            val apiResult = apiClient.getResponse<OctaneGGMatch>(
                endpoint = OctaneGGEndpoints.matchDetailEndpoint(matchId),
            )

            val mappedResult = when (apiResult) {
                is DataState.Loading -> DataState.Loading
                is DataState.Success -> {
                    val mappedMatch = apiResult.data.toMatch()

                    if (mappedMatch != null) {
                        DataState.Success(mappedMatch)
                    } else {
                        val err = Throwable("Unable to parse match for id: $matchId")
                        DataState.Error(err)
                    }
                }
                is DataState.Error -> {
                    DataState.Error(apiResult.error)
                }
            }

            emit(mappedResult)
        }
    }

    override fun fetchMatches(request: MatchListRequest): Flow<DataState<List<Match>>> {
        return flow {
            val apiResult = apiClient.getResponse<OctaneGGMatchListResponse>(
                endpoint = OctaneGGEndpoints.MATCHES,
                requestBuilder = {
                    addMatchParameters(request)
                },
            )

            val mappedResult = when (apiResult) {
                is DataState.Loading -> DataState.Loading
                is DataState.Success -> {
                    val mappedMatches =
                        apiResult.data.matches?.mapNotNull(OctaneGGMatch::toMatch).orEmpty()

                    val sortedMatches = mappedMatches.sortedByDescending(Match::date)

                    DataState.Success(sortedMatches)
                }
                is DataState.Error -> {
                    DataState.Error(apiResult.error)
                }
            }

            emit(mappedResult)
        }
    }
}

private fun HttpRequestBuilder.addMatchParameters(request: MatchListRequest) {
    val dateTimeFormatter = DateTimeFormatter()
    val octaneGGDateFormat = "yyyy-MM-dd"

    if (request.after != null) {
        val afterString = dateTimeFormatter.formatLocalDateTime(request.after, octaneGGDateFormat)
        this.parameter("after", afterString)
    }

    if (request.before != null) {
        val beforeString = dateTimeFormatter.formatLocalDateTime(request.before, octaneGGDateFormat)
        this.parameter("before", beforeString)
    }

    if (request.group != null) {
        this.parameter("group", request.group)
    }

    if (request.eventId != null) {
        this.parameter("event", request.eventId)
    }

    if (request.stageId != null) {
        this.parameter("stage", request.stageId)
    }

    if (request.region != null) {
        this.parameter("region", request.region)
    }
}
