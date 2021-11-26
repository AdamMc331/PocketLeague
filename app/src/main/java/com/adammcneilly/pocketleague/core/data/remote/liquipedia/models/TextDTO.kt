package com.adammcneilly.pocketleague.core.data.remote.liquipedia.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * A portion of the [LiquipediaPageParseResponseDTO] that shows the text of the page.
 */
@JsonClass(generateAdapter = true)
data class TextDTO(
    @Json(name = "*")
    val x: String? = null
)
