package com.adammcneilly.pocketleague.core.data.remote.liquipedia

import com.adammcneilly.pocketleague.core.data.remote.liquipedia.models.LiquipediaPageParseResponseDTO
import retrofit2.Response
import retrofit2.http.GET

interface LiquipediaRetrofitAPI {
    @GET("/rocketleague/api.php?action=parse&page=Portal:Teams&format=json")
    suspend fun fetchTeamsPage(): Response<LiquipediaPageParseResponseDTO>
}
