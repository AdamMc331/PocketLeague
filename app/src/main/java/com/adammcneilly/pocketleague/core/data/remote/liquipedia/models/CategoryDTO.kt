package com.adammcneilly.pocketleague.core.data.remote.liquipedia.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoryDTO(
    @Json(name = "sortkey")
    val sortKey: String? = null,
    @Json(name = "*")
    val x: String? = null
)
