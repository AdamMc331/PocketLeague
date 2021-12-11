package com.adammcneilly.pocketleague.core.data.remote.liquipedia.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * A DTO generated for our [LiquipediaPageParseResponseDTO].
 */
@JsonClass(generateAdapter = true)
data class SectionDTO(
    @Json(name = "anchor")
    val anchor: String? = null,
    @Json(name = "byteoffset")
    val byteOffset: Int? = null,
    @Json(name = "fromtitle")
    val fromTitle: String? = null,
    @Json(name = "index")
    val index: String? = null,
    @Json(name = "level")
    val level: String? = null,
    @Json(name = "line")
    val line: String? = null,
    @Json(name = "number")
    val number: String? = null,
    @Json(name = "toclevel")
    val tocLevel: Int? = null
)
