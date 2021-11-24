package com.adammcneilly.pocketleague.core.html

interface HTMLParser {
    fun setHTML(html: String)

    fun selectAll(
        elementType: String,
        elementClass: String,
    ): List<HTMLElement>
}
