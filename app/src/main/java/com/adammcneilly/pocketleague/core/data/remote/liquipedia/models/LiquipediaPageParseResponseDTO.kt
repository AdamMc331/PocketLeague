package com.adammcneilly.pocketleague.core.data.remote.liquipedia.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * A DTO formatted based on the liquipedia REST api for parsing a page.
 */
@JsonClass(generateAdapter = true)
data class LiquipediaPageParseResponseDTO(
    @Json(name = "parse")
    val parse: ParseDTO? = null
)
