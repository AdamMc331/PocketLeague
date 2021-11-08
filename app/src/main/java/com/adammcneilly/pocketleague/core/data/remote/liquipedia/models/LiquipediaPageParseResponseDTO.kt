package com.adammcneilly.pocketleague.core.data.remote.liquipedia.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LiquipediaPageParseResponseDTO(
    @Json(name = "parse")
    val parse: ParseDTO? = null
)
