package com.adammcneilly.pocketleague.core.data.remote.liquipedia.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ParseDTO(
    @Json(name = "categories")
    val categories: List<CategoryDTO>? = null,
    @Json(name = "displaytitle")
    val displayTitle: String? = null,
    @Json(name = "externallinks")
    val externalLinks: List<Any>? = null,
    @Json(name = "images")
    val images: List<String>? = null,
    @Json(name = "iwlinks")
    val iwLinks: List<Any>? = null,
    @Json(name = "langlinks")
    val langLinks: List<Any>? = null,
    @Json(name = "links")
    val links: List<LinkDTO>? = null,
    @Json(name = "pageid")
    val pageId: Int? = null,
    @Json(name = "parsewarnings")
    val parseWarnings: List<String>? = null,
    @Json(name = "properties")
    val properties: List<PropertyDTO>? = null,
    @Json(name = "revid")
    val revId: Int? = null,
    @Json(name = "sections")
    val sections: List<SectionDTO>? = null,
    @Json(name = "templates")
    val templates: List<TemplateDTO>? = null,
    @Json(name = "text")
    val text: TextDTO? = null,
    @Json(name = "title")
    val title: String? = null
)
