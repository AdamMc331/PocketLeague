package com.adammcneilly.pocketleague.core.data.remote.liquipedia.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LinkDTO(
    @Json(name = "exists")
    val exists: String? = null,
    @Json(name = "ns")
    val ns: Int? = null,
    @Json(name = "*")
    val x: String? = null
)
