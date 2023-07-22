package com.adammcneilly.pocketleague.data.match

import com.adammcneilly.pocketleague.core.datetime.defaultClock
import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.data.octanegg.OctaneGGAPIClient
import com.adammcneilly.pocketleague.data.octanegg.models.OctaneGGMatch
import com.adammcneilly.pocketleague.data.octanegg.models.OctaneGGMatchListResponse
import com.adammcneilly.pocketleague.data.octanegg.models.toMatch
import com.adammcneilly.pocketleague.data.remote.BaseKTORClient
import kotlinx.datetime.Clock
import kotlin.time.Duration.Companion.days

/**
 * An implementation of [RemoteMatchService] that requests
 * data from the supplied [apiClient].
 */
class OctaneGGMatchService(
    private val apiClient: BaseKTORClient,
    private val clock: Clock,
) : RemoteMatchService {

    constructor() : this(OctaneGGAPIClient, defaultClock())

    override suspend fun getMatchDetail(matchId: String): Result<Match> {
        return apiClient.getResponse<OctaneGGMatch>(
            endpoint = "$MATCHES_ENDPOINT/$matchId",
        ).map { octaneMatch ->
            octaneMatch.toMatch()
        }
    }

    override suspend fun getPastWeeksMatches(): Result<List<Match>> {
        return apiClient.getResponse<OctaneGGMatchListResponse>(
            endpoint = MATCHES_ENDPOINT,
            params = mapOf(
                "before" to clock.now(),
                "after" to clock.now().minus(NUM_DAYS_RECENT_MATCHES.days),
                "group" to "rlcs",
            ),
        ).map { octaneMatchListResponse ->
            val mappedMatches =
                octaneMatchListResponse.matches?.map(OctaneGGMatch::toMatch).orEmpty()

            mappedMatches
        }
    }

    override suspend fun getUpcomingMatches(): Result<List<Match>> {
        return apiClient.getResponse<OctaneGGMatchListResponse>(
            endpoint = MATCHES_ENDPOINT,
            params = mapOf(
                "after" to clock.now(),
                "group" to "rlcs",
            ),
        ).map { octaneMatchListResponse ->
            val mappedMatches =
                octaneMatchListResponse.matches?.map(OctaneGGMatch::toMatch).orEmpty()

            mappedMatches
        }
    }

    override suspend fun getMatchesForEventStage(
        eventId: Event.Id,
        stageId: String,
    ): Result<List<Match>> {
        return apiClient.getResponse<OctaneGGMatchListResponse>(
            endpoint = MATCHES_ENDPOINT,
            params = mapOf(
                "event" to eventId.id,
                "stage" to stageId,
            ),
        ).map { octaneGGMatchListResponse ->
            octaneGGMatchListResponse.matches?.map(OctaneGGMatch::toMatch).orEmpty()
        }
    }

    companion object {
        private const val MATCHES_ENDPOINT = "/matches"
        private const val NUM_DAYS_RECENT_MATCHES = 7
    }
}
