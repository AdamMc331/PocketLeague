package com.adammcneilly.pocketleague.shared.data.remote.octanegg.services

import com.adammcneilly.pocketleague.shared.data.DataResult
import com.adammcneilly.pocketleague.shared.data.models.MatchListRequest
import com.adammcneilly.pocketleague.shared.data.remote.octanegg.OctaneGGAPIClient
import com.adammcneilly.pocketleague.shared.data.remote.octanegg.OctaneGGEndpoints
import com.adammcneilly.pocketleague.shared.data.remote.octanegg.mappers.toMatch
import com.adammcneilly.pocketleague.shared.data.remote.octanegg.models.OctaneGGMatch
import com.adammcneilly.pocketleague.shared.data.remote.octanegg.models.OctaneGGMatchListResponse
import com.adammcneilly.pocketleague.shared.data.repositories.MatchRepository
import com.adammcneilly.pocketleague.shared.datetime.DateTimeFormatter
import com.adammcneilly.pocketleague.shared.models.Match
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

    override fun fetchMatches(request: MatchListRequest): Flow<DataResult<List<Match>>> {
        return flow {
            val apiResult = apiClient.getResponse<OctaneGGMatchListResponse>(
                endpoint = OctaneGGEndpoints.MATCHES,
                requestBuilder = {
                    addMatchParameters(request)
                },
            )

            val mappedResult = when (apiResult) {
                is DataResult.Success -> {
                    val mappedMatches = apiResult.data.matches?.map(OctaneGGMatch::toMatch).orEmpty()

                    val sortedMatches = mappedMatches.sortedByDescending(Match::date)

                    DataResult.Success(sortedMatches)
                }
                is DataResult.Error -> {
                    DataResult.Error(apiResult.error)
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
}
