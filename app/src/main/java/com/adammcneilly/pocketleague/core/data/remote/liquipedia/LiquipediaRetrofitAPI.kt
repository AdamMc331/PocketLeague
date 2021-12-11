package com.adammcneilly.pocketleague.core.data.remote.liquipedia

import com.adammcneilly.pocketleague.core.data.remote.liquipedia.models.LiquipediaPageParseResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * A Retrofit API interface that makes calls to the Liquipedia REST API.
 */
interface LiquipediaRetrofitAPI {

    /**
     * Requests the supplied [page] from the Liquipedia RL API.
     */
    @GET("/rocketleague/api.php?action=parse&format=json")
    suspend fun fetchPage(
        @Query("page") page: String,
    ): Response<LiquipediaPageParseResponseDTO>
}
