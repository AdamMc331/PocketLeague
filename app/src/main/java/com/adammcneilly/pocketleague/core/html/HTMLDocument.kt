package com.adammcneilly.pocketleague.core.html

interface HTMLDocument {
    fun selectAll(
        elementType: String,
        elementClass: String,
    ): List<HTMLElement>
}
