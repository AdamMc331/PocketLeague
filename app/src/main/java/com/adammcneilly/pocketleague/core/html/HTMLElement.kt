package com.adammcneilly.pocketleague.core.html

interface HTMLElement {
    fun getText(): String

    fun selectAll(
        elementType: String,
        elementClass: String,
    ): List<HTMLElement>

    fun getAttribute(
        attributeKey: String,
    ): String
}
