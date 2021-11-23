package com.adammcneilly.pocketleague.core.data.remote.liquipedia

import com.adammcneilly.pocketleague.core.data.remote.liquipedia.models.LiquipediaPageParseResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LiquipediaRetrofitAPI {
    @GET("/rocketleague/api.php?action=parse&format=json")
    suspend fun fetchPage(
        @Query("page") page: String,
    ): Response<LiquipediaPageParseResponseDTO>

    @GET("/rocketleague/api.php?action=parse&page=Portal:Teams&format=json")
    suspend fun fetchTeamsPage(): Response<LiquipediaPageParseResponseDTO>
}
